package com.dignicate.zero1.domain.subject01.case101

import com.dignicate.zero1.domain.subject01.CompanyInfo
import io.reactivex.Single

interface SimpleCompanyInfoRepositoryInterface {
    fun fetch(id: CompanyInfo.Id): Single<CompanyInfo>
}