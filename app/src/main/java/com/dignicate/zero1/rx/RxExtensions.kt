package com.dignicate.zero1.rx

import android.widget.TextView
import io.reactivex.Observable
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.subjects.PublishSubject
import timber.log.Timber

typealias DisposeBag = CompositeDisposable

fun Disposable.disposedBy(disposeBag: DisposeBag) {
    disposeBag.add(this)
}

fun <T> Observable<T>.bindTo(subject: PublishSubject<T>): Disposable {
    return subscribe(
        onNext@ { it?.let { subject.onNext(it) } },
        onError@ { Timber.e(it) }
    )
}

fun Observable<String>.bindTo(textView: TextView): Disposable {
    // TODO: subscribe on main thread.
    return subscribe(
        onNext@ { it?.let { textView.text = it } },
        onError@ { Timber.e(it) }
    )
}
