package com.dignicate.zero1.domain.subject01.case102.manualdi

import com.dignicate.zero1.domain.subject01.CompanyInfo
import com.dignicate.zero1.domain.subject01.case101.SimpleCompanyInfoRepositoryInterface
import com.dignicate.zero1.rx.DisposeBag
import com.dignicate.zero1.rx.RxExtensions.bindTo
import com.dignicate.zero1.rx.RxExtensions.disposedBy
import io.reactivex.Observable
import io.reactivex.subjects.PublishSubject

class FetchWithDataStateUseCase(disposeBag: DisposeBag,
                                repository: SimpleCompanyInfoRepositoryInterface
) {

    sealed class DataState {
        class Success(val data: CompanyInfo) : DataState()
        object InProgress : DataState()
        object Failure : DataState()
    }

    private val fetchTrigger = PublishSubject.create<CompanyInfo.Id>()

    private val companyInfoDataStateSubject = PublishSubject.create<DataState>()

    init {
        fetchTrigger
            .flatMapSingle { repository.fetch(it) }
            .map { DataState.Success(it) }
            .bindTo(companyInfoDataStateSubject)
            .disposedBy(disposeBag)

    }

    val companyInfo: Observable<CompanyInfo>
        get() =
            companyInfoDataStateSubject
                .switchMap {
                    when (it) {
                        is DataState.Success -> Observable.just(it.data)
                        is DataState.InProgress, DataState.Failure -> Observable.empty()
                    }
                }

    val isInProgress: Observable<Boolean>
        get() =
            companyInfoDataStateSubject
                .map {
                    when (it) {
                        is DataState.InProgress -> true
                        is DataState.Success, DataState.Failure -> false
                    }
                }
                .distinctUntilChanged()

    val isFailure: Observable<Boolean>
        get() =
            companyInfoDataStateSubject
                .map {
                    when (it) {
                        is DataState.Failure -> true
                        is DataState.Success, DataState.InProgress -> false
                    }
                }
                .distinctUntilChanged()

    fun fetch(id: Int) {
        companyInfoDataStateSubject.onNext(DataState.InProgress)
        fetchTrigger.onNext(CompanyInfo.Id(id))
    }
}