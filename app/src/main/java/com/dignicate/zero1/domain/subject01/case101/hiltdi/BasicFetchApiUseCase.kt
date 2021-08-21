package com.dignicate.zero1.domain.subject01.case101.hiltdi

import com.dignicate.zero1.domain.subject01.CompanyInfo
import com.dignicate.zero1.domain.subject01.case101.SimpleCompanyInfoRepositoryInterface
import com.dignicate.zero1.rx.DisposeBag
import com.dignicate.zero1.rx.RxExtensions.bindTo
import com.dignicate.zero1.rx.RxExtensions.disposedBy
import io.reactivex.Observable
import io.reactivex.subjects.BehaviorSubject
import io.reactivex.subjects.PublishSubject
import javax.inject.Inject

interface BasicFetchApiUseCaseInterface {
    val companyInfo: CompanyInfo?
    fun fetch(id: Int)
}

class BasicFetchApiUse @Inject constructor(repository: SimpleCompanyInfoRepositoryInterface) : BasicFetchApiUseCaseInterface {

    private val fetchTrigger = PublishSubject.create<CompanyInfo.Id>()

    private val companyInfoSubject = BehaviorSubject.create<CompanyInfo>()

    private val disposeBag = DisposeBag()

    override val companyInfo: CompanyInfo?
        get() = companyInfoSubject.value

    init {
        fetchTrigger
            .flatMapSingle {
                repository.fetch(it)
            }
            .bindTo(companyInfoSubject)
            .disposedBy(disposeBag)
    }

    override fun fetch(id: Int) {
        fetchTrigger.onNext(CompanyInfo.Id(id))
    }
}