package com.dignicate.zero1.rx

import android.view.View
import android.widget.TextView
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.subjects.PublishSubject
import timber.log.Timber

typealias DisposeBag = CompositeDisposable

fun Disposable.disposedBy(disposeBag: DisposeBag) {
    disposeBag.add(this)
}

fun <T> Observable<out T>.bindTo(subject: PublishSubject<T>): Disposable {
    return subscribe(
        onNext@ { it?.let { subject.onNext(it) } },
        onError@ { Timber.e(it) }
    )
}

fun Observable<String>.bindTo(textView: TextView): Disposable {
    return subscribeOn(AndroidSchedulers.mainThread())
        .subscribe(
            onNext@ { it?.let { textView.text = it } },
            onError@ { Timber.e(it) }
        )
}

fun Observable<Int>.bindVisibilityTo(view: View): Disposable {
    return subscribeOn(AndroidSchedulers.mainThread())
        .subscribe(
            onNext@ { it?.let { view.visibility = it } },
            onError@ { Timber.e(it) }
        )
}
