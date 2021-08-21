package com.dignicate.zero1.domain.subject01.case101.hiltdi

import com.dignicate.zero1.domain.subject01.CompanyInfo
import io.reactivex.Observable
import io.reactivex.subjects.PublishSubject
import javax.inject.Inject

interface BasicFetchApiUseCaseInterface {
    val companyInfo: Observable<CompanyInfo>
    fun fetch(id: Int)
}

class BasicFetchApiUse @Inject constructor() : BasicFetchApiUseCaseInterface {

    private val fetchTrigger = PublishSubject.create<CompanyInfo.Id>()

    private val companyInfoSubject = PublishSubject.create<CompanyInfo>()

    override val companyInfo: Observable<CompanyInfo>
        get() = companyInfoSubject

    override fun fetch(id: Int) {
        fetchTrigger.onNext(CompanyInfo.Id(id))
    }
}