package com.dignicate.zero1.domain.subject01.case103

import com.dignicate.zero1.domain.subject01.CompanyInfo
import com.dignicate.zero1.rx.DisposeBag
import com.dignicate.zero1.rx.bindTo
import com.dignicate.zero1.rx.disposedBy
import io.reactivex.Observable
import io.reactivex.subjects.BehaviorSubject
import io.reactivex.subjects.PublishSubject
import java.util.concurrent.TimeUnit

class FetchAndSaveDataUseCase(private val disposeBag: DisposeBag,
                              private val repository: CompanyInfoFetchAndSaveRepositoryInterface) {

    private val fetchTrigger = PublishSubject.create<CompanyInfo.Id>()

    private val fetchLastUpdatedTrigger = PublishSubject.create<Unit>()

    private val saveTrigger = PublishSubject.create<CompanyInfo>()

    private val clearLocalDataTrigger = PublishSubject.create<Unit>()

    private val dataStateRelay = PublishSubject.create<DataState>()

    private val processStateRelay = BehaviorSubject.createDefault<ProcessState>(ProcessState.NoProcess)

    private val saveCompleteRelay = PublishSubject.create<Unit>()

    private val lastUpdatedRelay = PublishSubject.create<String>()

    val processState: Observable<ProcessState>
        get() = processStateRelay

    val saveComplete: Observable<Unit>
        get() = saveCompleteRelay

    val lastUpdated: Observable<String>
        get() = lastUpdatedRelay

    sealed class DataState {
        class Remote(val data: CompanyInfo) : DataState()
        class Local(val data: CompanyInfo) : DataState()
        object NoData : DataState()
    }

    sealed class ProcessState {
        object NoProcess : ProcessState()
        object Fetching : ProcessState()
        object FetchedLocally : ProcessState()
        object Saving : ProcessState()
        object Saved : ProcessState()

        val isFetchAvailable: Boolean
            get() = when(this) {
                is NoProcess, FetchedLocally, Saved -> true
                is Fetching, Saving -> false
            }

        val isClearAvailable: Boolean
            get() = when(this) {
                is NoProcess, FetchedLocally, Saved -> true
                is Fetching, Saving -> false
            }
    }

    init {
        setupBinding()
    }

    private fun setupBinding() {
        fetchTrigger
            .flatMapSingle { id ->
                repository.fetch(id = id)
            }
            .bindTo(dataStateRelay)
            .disposedBy(disposeBag)

        fetchTrigger
            .map { ProcessState.Fetching }
            .bindTo(processStateRelay)
            .disposedBy(disposeBag)

        dataStateRelay
            .switchMap {
                when(it) {
                    is DataState.Remote -> Observable.just(it.data)
                    is DataState.Local, DataState.NoData -> Observable.empty()
                }
            }
            .bindTo(saveTrigger)
            .disposedBy(disposeBag)

        val localFetchTrigger = dataStateRelay
            .filter {
                when (it) {
                    is DataState.Remote, DataState.NoData -> false
                    is DataState.Local -> true
                }
            }
            .map { }
            .delay(500, TimeUnit.MILLISECONDS)

        localFetchTrigger
            .bindTo(fetchLastUpdatedTrigger)
            .disposedBy(disposeBag)

        localFetchTrigger
            .map { ProcessState.FetchedLocally }
            .bindTo(processStateRelay)
            .disposedBy(disposeBag)

        saveTrigger
            .flatMapSingle {
                repository.saveToLocal(it)
            }
            .bindTo(saveCompleteRelay)
            .disposedBy(disposeBag)

        saveTrigger
            .map { ProcessState.Saving }
            .bindTo(processStateRelay)
            .disposedBy(disposeBag)

        saveCompleteRelay
            .bindTo(fetchLastUpdatedTrigger)
            .disposedBy(disposeBag)

        saveCompleteRelay
            .map { ProcessState.Saved }
            .bindTo(processStateRelay)
            .disposedBy(disposeBag)
    }

}