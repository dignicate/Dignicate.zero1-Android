package com.dignicate.zero1.ui.subject01.case101.hiltdi

import androidx.lifecycle.ViewModel
import com.dignicate.zero1.rx.DisposeBag
import dagger.hilt.android.lifecycle.HiltViewModel

@HiltViewModel
class BasicFetchApiViewModel : ViewModel() {

    private val disposeBag = DisposeBag()
}