package com.dignicate.zero1.ui.subject01.case101.hiltdi

import androidx.lifecycle.ViewModel
import com.dignicate.zero1.domain.subject01.case101.hiltdi.BasicFetchApiUseCaseInterface
import com.dignicate.zero1.rx.DisposeBag
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.Observable
import javax.inject.Inject

@HiltViewModel
class BasicFetchApiViewModel @Inject constructor(private val useCase: BasicFetchApiUseCaseInterface) : ViewModel() {

    private val disposeBag = DisposeBag()

    data class Data(
        val companyNameJP: Observable<String>,
        val companyNameEN: Observable<String>
    )

    val data: Data
        get() = Data(
            companyNameJP = useCase.companyInfo.map { it.nameJP },
            companyNameEN = useCase.companyInfo.map { it.nameEN }
        )

//    val companyNameJP: Observable<String>
//        get() = useCase.companyInfo.map { it.nameJP }
//
//    val companyNameEN: Observable<String>
//        get() = useCase.companyInfo.map { it.nameEN }

    val address: Observable<String>
        get() = useCase.companyInfo.map { it.address }

    val foundationDate: Observable<String>
        get() = useCase.companyInfo.map { it.foundationDate.localizedExpression }

    val capital: Observable<String>
        get() = useCase.companyInfo.map { it.capital.localizedExpression }

    val numberOfEmployees: Observable<String>
        get() = useCase.companyInfo.map { "${it.numberOfEmployees}名" }

    override fun onCleared() {
        super.onCleared()
        disposeBag.clear()
    }

    fun didTapFetchButton(id: Int) {
        useCase.fetch(id)
    }
}