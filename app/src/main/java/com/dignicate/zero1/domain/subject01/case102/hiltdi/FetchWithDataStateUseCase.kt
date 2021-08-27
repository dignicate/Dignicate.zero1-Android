package com.dignicate.zero1.domain.subject01.case102.hiltdi

import com.dignicate.zero1.domain.subject01.CompanyInfo
import com.dignicate.zero1.domain.subject01.case101.SimpleCompanyInfoRepositoryInterface
import com.dignicate.zero1.rx.DisposeBag
import com.dignicate.zero1.rx.RxExtensions.bindTo
import com.dignicate.zero1.rx.RxExtensions.disposedBy
import io.reactivex.Observable
import io.reactivex.subjects.PublishSubject
import javax.inject.Inject
import com.dignicate.zero1.domain.subject01.case102.hiltdi.FetchWithDataStateUseCase.DataState.InProgress
import com.dignicate.zero1.domain.subject01.case102.hiltdi.FetchWithDataStateUseCase.DataState.Failure
import com.dignicate.zero1.domain.subject01.case102.hiltdi.FetchWithDataStateUseCase.DataState.Success

interface FetchWithDataStateUseCaseInterface {
    val companyInfo: Observable<CompanyInfo>
    val isInProgress: Observable<Boolean>
    fun fetch(id: Int)
    fun dispose()
}

class FetchWithDataStateUseCase @Inject constructor(repository: SimpleCompanyInfoRepositoryInterface) : FetchWithDataStateUseCaseInterface {

    sealed class DataState {
        class Success(val data: CompanyInfo) : DataState()
        object InProgress : DataState()
        object Failure : DataState()
    }

    private val fetchTrigger = PublishSubject.create<CompanyInfo.Id>()

    private val companyInfoDataStateSubject = PublishSubject.create<DataState>()

    private val disposeBag = DisposeBag()

    override val companyInfo: Observable<CompanyInfo>
        get() =
            companyInfoDataStateSubject
                .switchMap {
                    when (it) {
                        is Success -> Observable.just(it.data)
                        is InProgress, Failure -> Observable.empty()
                    }
                }

    override val isInProgress: Observable<Boolean>
        get() =
            companyInfoDataStateSubject
                .map {
                    when (it) {
                        is InProgress -> true
                        is Success, Failure -> false
                    }
                }
                .distinctUntilChanged()

    val isFailure: Observable<Boolean>
        get() =
            companyInfoDataStateSubject
                .map {
                    when (it) {
                        is Failure -> true
                        is Success, InProgress -> false
                    }
                }
                .distinctUntilChanged()

    init {
        fetchTrigger
            .flatMapSingle { repository.fetch(it) }
            .map { Success(it) }
            .bindTo(companyInfoDataStateSubject)
            .disposedBy(disposeBag)
    }

    override fun fetch(id: Int) {
        companyInfoDataStateSubject.onNext(InProgress)
        fetchTrigger.onNext(CompanyInfo.Id(id))
    }

    override fun dispose() {
        disposeBag.clear()
    }
}
