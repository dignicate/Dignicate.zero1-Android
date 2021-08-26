package com.dignicate.zero1.ui.subject01.case102.hiltdi

import androidx.lifecycle.ViewModel
import com.dignicate.zero1.rx.DisposeBag
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class FetchWithDataStateViewModel @Inject constructor() : ViewModel() {

    private val disposeBag = DisposeBag()

    override fun onCleared() {
        super.onCleared()
        disposeBag.clear()
    }
}