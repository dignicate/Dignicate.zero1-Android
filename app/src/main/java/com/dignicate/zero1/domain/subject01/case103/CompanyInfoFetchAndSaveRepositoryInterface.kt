package com.dignicate.zero1.domain.subject01.case103

import com.dignicate.zero1.domain.subject01.CompanyInfo
import com.dignicate.zero1.domain.subject01.case102.FetchWithDataStateUseCase
import io.reactivex.Single
import java.util.*

interface CompanyInfoFetchAndSaveRepositoryInterface {
    fun fetch(id: CompanyInfo.Id): Single<FetchAndSaveDataUseCase.DataState>
    fun fetchLastUpdated(): Single<Date?>
    fun saveToLocal(companyInfo: CompanyInfo): Single<Unit>
    fun clearLocalData(): Single<Unit>
}