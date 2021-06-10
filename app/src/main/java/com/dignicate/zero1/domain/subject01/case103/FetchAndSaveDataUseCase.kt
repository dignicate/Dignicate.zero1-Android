package com.dignicate.zero1.domain.subject01.case103

import com.dignicate.zero1.domain.subject01.CompanyInfo
import com.dignicate.zero1.rx.DisposeBag
import com.dignicate.zero1.rx.bindTo
import com.dignicate.zero1.rx.disposedBy
import io.reactivex.Observable
import io.reactivex.subjects.PublishSubject

class FetchAndSaveDataUseCase(disposeBag: DisposeBag,
                              repository: CompanyInfoFetchAndSaveRepositoryInterface) {

    sealed class DataState {
        class Remote(val data: CompanyInfo) : DataState()
        class Local(val data: CompanyInfo) : DataState()
        object NoData : DataState()
    }

    sealed class ProcessState {
        object NoProcess : ProcessState()
        object Fetching : ProcessState()

        val isFetchAvailable: Boolean
            get() = when(this) {
                is NoProcess -> true
                is Fetching -> false
            }

        val isClearAvailable: Boolean
            get() = when(this) {
                is NoProcess -> true
                is Fetching -> false
            }
    }

    private val fetchTrigger = PublishSubject.create<CompanyInfo.Id>()

    private val fetchLastUpdatedTrigger = PublishSubject.create<Void>()

    private val saveTrigger = PublishSubject.create<CompanyInfo>()

    private val clearLocalDataTrigger = PublishSubject.create<Unit>()
}