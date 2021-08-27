package com.dignicate.zero1.domain.subject01.case102.hiltdi

import com.dignicate.zero1.domain.subject01.CompanyInfo
import com.dignicate.zero1.domain.subject01.case102.manualdi.FetchWithDataStateUseCase
import io.reactivex.subjects.PublishSubject
import javax.inject.Inject

interface FetchWithDataStateUseCaseInterface {

}

class FetchWithDataStateUseCase @Inject constructor() : FetchWithDataStateUseCaseInterface {

    sealed class DataState {
        class Success(val data: CompanyInfo) : DataState()
        object InProgress : DataState()
        object Failure : DataState()
    }

    private val fetchTrigger = PublishSubject.create<CompanyInfo.Id>()

    private val companyInfoDataStateSubject = PublishSubject.create<FetchWithDataStateUseCase.DataState>()

}