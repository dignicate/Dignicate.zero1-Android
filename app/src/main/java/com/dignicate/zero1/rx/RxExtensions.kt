package com.dignicate.zero1.rx

import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable

typealias DisposeBag = CompositeDisposable

fun Disposable.disposedBy(disposeBag: DisposeBag) {
    disposeBag.add(this)
}
