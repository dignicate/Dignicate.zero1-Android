package com.dignicate.zero1.ui.subject01.case103

import android.content.SharedPreferences
import androidx.lifecycle.ViewModel
import com.dignicate.zero1.domain.subject01.CompanyInfo
import com.dignicate.zero1.domain.subject01.case103.FetchAndSaveDataUseCase
import com.dignicate.zero1.infra.mock.subject01.CompanyInfoFetchAndSaveRepositoryMock
import com.dignicate.zero1.rx.DisposeBag
import io.reactivex.Observable
import java.util.*

class FetchAndSaveDataViewModel : ViewModel() {

    private val disposeBag = DisposeBag()

    private lateinit var useCase: FetchAndSaveDataUseCase

    fun onActivityCreated(sharedPreferences: SharedPreferences) {
        useCase = FetchAndSaveDataUseCase(disposeBag, CompanyInfoFetchAndSaveRepositoryMock(2500, sharedPreferences))
    }

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
        get() =
            Observable.combineLatest(
                useCase.dataState,
                useCase.lastUpdated
            ) { _, lastUpdated ->
                lastUpdated.value?.toString() ?: ""
            }
                .startWith("")
                .distinctUntilChanged()

    val dataState: Observable<String>
        get() =
            useCase.dataState
                .map {
                    when (it) {
                        is FetchAndSaveDataUseCase.DataState.Remote -> "リモート（モック）"
                        is FetchAndSaveDataUseCase.DataState.Local -> "ローカル保存"
                        is FetchAndSaveDataUseCase.DataState.NoData -> ""
                   }
                }
                .startWith("")
                .distinctUntilChanged()

    val processState: Observable<String>
        get() =
            useCase.processState
                .map {
                    when (it) {
                        is FetchAndSaveDataUseCase.ProcessState.NoProcess -> "初期状態"
                        is FetchAndSaveDataUseCase.ProcessState.Fetching -> "取得中"
                        is FetchAndSaveDataUseCase.ProcessState.FetchedLocally -> "端末から取得"
                        is FetchAndSaveDataUseCase.ProcessState.Saving -> "内部保存中"
                        is FetchAndSaveDataUseCase.ProcessState.Saved -> "保存完了"
                        is FetchAndSaveDataUseCase.ProcessState.Clearing -> "消去中"
                        is FetchAndSaveDataUseCase.ProcessState.Cleared -> "消去完了"
                    }
                }
                .distinctUntilChanged()

    val shouldEnableClearButton: Observable<Boolean>
        get() =
            useCase.processState
                .map { it.isClearAvailable }
                .distinctUntilChanged()

    val shouldEnableFetchButton: Observable<Boolean>
        get() =
            useCase.processState
                .map { it.isFetchAvailable }
                .distinctUntilChanged()

    fun didTapFetchButton(id: Int) {
        useCase.fetch(CompanyInfo.Id(id))
    }

    fun didTapClearButton() {
        useCase.clear()
    }
}