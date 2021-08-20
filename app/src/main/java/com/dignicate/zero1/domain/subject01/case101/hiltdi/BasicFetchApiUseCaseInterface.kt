package com.dignicate.zero1.domain.subject01.case101.hiltdi

import com.dignicate.zero1.domain.subject01.CompanyInfo
import io.reactivex.Observable

interface BasicFetchApiUseCaseInterface {
    val companyInfo: Observable<CompanyInfo>
    fun fetch(id: Int)
}
