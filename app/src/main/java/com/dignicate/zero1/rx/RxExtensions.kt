package com.dignicate.zero1.rx

import android.view.View
import android.widget.Button
import android.widget.TextView
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.subjects.Subject
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import timber.log.Timber
import java.util.*

typealias DisposeBag = CompositeDisposable

object RxExtensions {
    sealed class Optional<T> {
        class Some<T>(val data: T) : Optional<T>()
        class None<T> : Optional<T>()

        val value: T?
            get() = when (this) {
                is Some -> data
                is None -> null
            }
    }

    fun Disposable.disposedBy(disposeBag: DisposeBag) {
        disposeBag.add(this)
    }

    fun <T> Observable<out T>.bindTo(subject: Subject<T>): Disposable {
        return subscribeOn(AndroidSchedulers.mainThread())
            .subscribe(
                onNext@ { it?.let { subject.onNext(it) } },
                onError@ { Timber.e(it) }
            )
    }

    fun Observable<String>.bindTextTo(textView: TextView): Disposable {
        return subscribeOn(AndroidSchedulers.mainThread())
            .subscribe(
                onNext@ {
                    GlobalScope.launch(Dispatchers.Main) {
                        textView.text = it
                    }
                },
                onError@ { Timber.e(it) }
            )
    }

    fun Observable<Int>.bindVisibilityTo(view: View): Disposable {
        return subscribeOn(AndroidSchedulers.mainThread())
            .subscribe(
                onNext@ {
                    GlobalScope.launch(Dispatchers.Main) {
                        view.visibility = it
                    }
                },
                onError@ { Timber.e(it) }
            )
    }

    fun Observable<Boolean>.bindEnabledTo(button: Button): Disposable {
        return subscribeOn(AndroidSchedulers.mainThread())
            .subscribe(
                onNext@ {
                    GlobalScope.launch(Dispatchers.Main) {
                        button.isEnabled = it
                    }
                },
                onError@ { Timber.e(it) }
            )
    }
}

