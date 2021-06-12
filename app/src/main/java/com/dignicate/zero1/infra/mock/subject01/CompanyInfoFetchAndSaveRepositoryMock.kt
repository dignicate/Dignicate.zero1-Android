package com.dignicate.zero1.infra.mock.subject01

import com.dignicate.zero1.domain.subject01.CompanyInfo
import com.dignicate.zero1.domain.subject01.case103.CompanyInfoFetchAndSaveRepositoryInterface
import com.dignicate.zero1.domain.subject01.case103.FetchAndSaveDataUseCase
import com.dignicate.zero1.rx.RxExtensions
import io.reactivex.Single
import java.util.*

class CompanyInfoFetchAndSaveRepositoryMock : CompanyInfoFetchAndSaveRepositoryInterface {
    override fun fetch(id: CompanyInfo.Id): Single<FetchAndSaveDataUseCase.DataState> {
        TODO("Not yet implemented")
    }

    override fun fetchLastUpdated(): Single<RxExtensions.Optional<Date>> {
        TODO("Not yet implemented")
    }

    override fun saveToLocal(companyInfo: CompanyInfo): Single<Unit> {
        TODO("Not yet implemented")
    }

    override fun clearLocalData(): Single<Unit> {
        TODO("Not yet implemented")
    }
}