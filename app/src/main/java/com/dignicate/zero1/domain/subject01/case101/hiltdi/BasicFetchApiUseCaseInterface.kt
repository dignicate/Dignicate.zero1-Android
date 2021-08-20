package com.dignicate.zero1.domain.subject01.case101.hiltdi

import com.dignicate.zero1.domain.subject01.CompanyInfo
import io.reactivex.Observable
import javax.inject.Inject

interface BasicFetchApiUseCaseInterface {
    val companyInfo: Observable<CompanyInfo>
    fun fetch(id: Int)
}

class BasicFetchApiUse @Inject constructor() : BasicFetchApiUseCaseInterface {
    override val companyInfo: Observable<CompanyInfo>
        get() = TODO("Not yet implemented")

    override fun fetch(id: Int) {
        TODO("Not yet implemented")
    }
}