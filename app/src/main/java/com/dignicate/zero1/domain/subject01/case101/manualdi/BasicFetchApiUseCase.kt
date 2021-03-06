package com.dignicate.zero1.domain.subject01.case101.manualdi

import com.dignicate.zero1.domain.subject01.CompanyInfo
import com.dignicate.zero1.domain.subject01.case101.SimpleCompanyInfoRepositoryInterface
import com.dignicate.zero1.rx.DisposeBag
import com.dignicate.zero1.rx.RxExtensions.bindTo
import com.dignicate.zero1.rx.RxExtensions.disposedBy
import io.reactivex.Observable
import io.reactivex.subjects.PublishSubject

class BasicFetchApiUseCase(disposeBag: DisposeBag,
                           repository: SimpleCompanyInfoRepositoryInterface
) {

    private val fetchTrigger = PublishSubject.create<CompanyInfo.Id>()

    private val companyInfoSubject = PublishSubject.create<CompanyInfo>()

    val companyInfo: Observable<CompanyInfo>
        get() = companyInfoSubject

    init {
        fetchTrigger
            .flatMapSingle {
                repository.fetch(it)
            }
            .bindTo(companyInfoSubject)
            .disposedBy(disposeBag)
    }

    fun fetch(id: Int) {
        fetchTrigger.onNext(CompanyInfo.Id(id))
    }
}
