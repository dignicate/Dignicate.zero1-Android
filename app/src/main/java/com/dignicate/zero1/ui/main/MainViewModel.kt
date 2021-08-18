package com.dignicate.zero1.ui.main

import androidx.lifecycle.ViewModel
import com.dignicate.zero1.rx.DisposeBag
import io.reactivex.Observable
import io.reactivex.subjects.PublishSubject

class MainViewModel : ViewModel() {

    private val disposeBag = DisposeBag()

    private val rowStatesSubject = PublishSubject.create<List<RowState>>()

    val rowStates: Observable<List<RowState>>
        get() = rowStatesSubject

    fun onActivityCreated() {
        rowStatesSubject.onNext(ContentStructure.rowStates)
    }

    override fun onCleared() {
        super.onCleared()
        disposeBag.clear()
    }


}