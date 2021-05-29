package com.dignicate.zero1.domain.subject01.case101

import com.dignicate.zero1.domain.subject01.SimpleCompanyInfoRepositoryInterface
import com.dignicate.zero1.rx.DisposeBag
import io.reactivex.subjects.PublishSubject

class BasicFetchApiUseCase(private val disposeBag: DisposeBag,
                           repository: SimpleCompanyInfoRepositoryInterface) {

    private val fetchTrigger = PublishSubject.create<Int>()

    init {
//        fetchTrigger
//            .flatMap {
//                repository.fetch(it)
//            }
//            .subscribe()
//            .disposedBy(disposeBag)
    }
}