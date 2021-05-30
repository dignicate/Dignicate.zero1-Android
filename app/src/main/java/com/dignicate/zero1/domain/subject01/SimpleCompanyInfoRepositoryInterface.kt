package com.dignicate.zero1.domain.subject01

import io.reactivex.Single

interface SimpleCompanyInfoRepositoryInterface {
    fun fetch(id: CompanyInfo.Id): Single<CompanyInfo>
}