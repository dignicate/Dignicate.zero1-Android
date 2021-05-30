package com.dignicate.zero1.ui.subject01.case101

import androidx.lifecycle.ViewModel
import com.dignicate.zero1.domain.subject01.case101.BasicFetchApiUseCase
import com.dignicate.zero1.infra.mock.subject01.SimpleCompanyInfoRepositoryMock
import com.dignicate.zero1.rx.DisposeBag
import io.reactivex.Observable

class BasicFetchApiViewModel : ViewModel() {

    private val disposeBag = DisposeBag()

    private val useCase = BasicFetchApiUseCase(
        disposeBag,
        SimpleCompanyInfoRepositoryMock()
    )

    val companyNameJP: Observable<String>
        get() = useCase.companyInfo.map { it.nameJP }

    val companyNameEN: Observable<String>
        get() = useCase.companyInfo.map { it.nameEN }

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