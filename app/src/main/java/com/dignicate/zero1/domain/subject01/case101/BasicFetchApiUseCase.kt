package com.dignicate.zero1.domain.subject01.case101

import com.dignicate.zero1.domain.subject01.CompanyInfo
import com.dignicate.zero1.domain.subject01.SimpleCompanyInfoRepositoryInterface
import com.dignicate.zero1.rx.DisposeBag
import com.dignicate.zero1.rx.disposedBy
import io.reactivex.Observable
import io.reactivex.Single
import io.reactivex.subjects.PublishSubject
import timber.log.Timber

class BasicFetchApiUseCase(private val disposeBag: DisposeBag,
                           repository: SimpleCompanyInfoRepositoryInterface) {

    private val fetchTrigger = PublishSubject.create<CompanyInfo.Id>()

    init {
        fetchTrigger
            .flatMapSingle {
                repository.fetch(it)
            }
            // TODO: Implement bind() extension.
            .subscribe()
            .disposedBy(disposeBag)

//        fetchTrigger
//            .flatMap {
//                repository.fetch(it)
//            }
//            .subscribe()
//            .disposedBy(disposeBag)
    }

    fun fetch(id: Int) {
        fetchTrigger.onNext(CompanyInfo.Id(id))
    }
}
