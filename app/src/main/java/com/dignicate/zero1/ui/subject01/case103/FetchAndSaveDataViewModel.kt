package com.dignicate.zero1.ui.subject01.case103

import android.content.SharedPreferences
import androidx.lifecycle.ViewModel
import com.dignicate.zero1.domain.subject01.case103.FetchAndSaveDataUseCase
import com.dignicate.zero1.infra.mock.subject01.CompanyInfoFetchAndSaveRepositoryMock
import com.dignicate.zero1.rx.DisposeBag
import io.reactivex.Observable
import java.util.*

class FetchAndSaveDataViewModel(sharedPreferences: SharedPreferences) : ViewModel() {

    private val disposeBag = DisposeBag()

    private val useCase = FetchAndSaveDataUseCase(
        disposeBag,
        CompanyInfoFetchAndSaveRepositoryMock(2000, sharedPreferences)
    )

    override fun onCleared() {
        super.onCleared()
        disposeBag.clear()
    }

    val companyNameJP: Observable<String>
        get() = useCase
            .dataState
            .map { it.companyInfo?.nameJP ?: "" }
            .startWith("")

    val companyNameEN: Observable<String>
        get() = useCase
            .dataState
            .map { it.companyInfo?.nameEN ?: "" }
            .startWith("")

    val lastUpdated: Observable<String>
        get() = Observable.combineLatest(
            useCase.dataState.map {},
            useCase.lastUpdated)
        { _, lastUpdated ->
            lastUpdated.value?.toString() ?: ""
        }
            .startWith("")
}