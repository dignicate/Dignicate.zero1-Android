package com.dignicate.zero1.domain.subject02.case101

import com.dignicate.zero1.domain.subject01.CompanyInfo
import io.reactivex.Observable

interface BasicFetchApiUseCase {
    val companyInfo: Observable<CompanyInfo>
    fun fetch(id: Int)
}
