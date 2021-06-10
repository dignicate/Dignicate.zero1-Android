package com.dignicate.zero1.domain.subject01.case103

import com.dignicate.zero1.domain.subject01.CompanyInfo
import io.reactivex.Single
import java.util.*

interface CompanyInfoFetchAndSaveRepositoryInterface {
    fun fetch(id: CompanyInfo.Id): Single<CompanyInfo>
    fun fetchLastUpdated(): Single<Date?>
    fun saveToLocal(companyInfo: CompanyInfo): Single<Unit>
    fun clearLocalData(): Single<Unit>
}