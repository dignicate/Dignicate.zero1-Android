package com.dignicate.zero1.ui.subject01.case102.manualdi

import android.view.View
import androidx.lifecycle.ViewModel
import com.dignicate.zero1.domain.subject01.case102.FetchWithDataStateUseCase
import com.dignicate.zero1.infra.mock.subject01.SimpleCompanyInfoRepositoryMock
import com.dignicate.zero1.rx.DisposeBag
import io.reactivex.Observable

class FetchWithDataStateViewModel : ViewModel() {

    private val disposeBag = DisposeBag()

    private val useCase = FetchWithDataStateUseCase(
        disposeBag,
        SimpleCompanyInfoRepositoryMock(5000)
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

    val visibilityOfProgress: Observable<Int>
        get() = useCase.isInProgress
            .map {
                if (it) View.VISIBLE else View.GONE
            }
            .distinctUntilChanged()
            .startWith(View.GONE)

    override fun onCleared() {
        super.onCleared()
        disposeBag.clear()
    }

    fun didTapFetchButton(id: Int) {
        useCase.fetch(id)
    }
}