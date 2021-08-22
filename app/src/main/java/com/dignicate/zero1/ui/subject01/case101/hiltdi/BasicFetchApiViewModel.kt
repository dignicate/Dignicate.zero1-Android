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
        val companyNameEN: Observable<String>,
        val address: Observable<String>,
        val foundationDate: Observable<String>
    )

    val data: Data
        get() = Data(
            companyNameJP = useCase.companyInfo.map { it.nameJP },
            companyNameEN = useCase.companyInfo.map { it.nameEN },
            address = useCase.companyInfo.map { it.address },
            foundationDate = useCase.companyInfo.map { it.foundationDate.localizedExpression }
        )

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