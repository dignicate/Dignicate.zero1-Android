package com.dignicate.zero1.ui.subject01.case102.hiltdi

import android.view.View
import androidx.lifecycle.ViewModel
import com.dignicate.zero1.domain.subject01.case102.hiltdi.FetchWithDataStateUseCaseInterface
import com.dignicate.zero1.rx.DisposeBag
import com.dignicate.zero1.ui.subject01.case101.hiltdi.CompanyInfoViewData
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.Observable
import javax.inject.Inject

@HiltViewModel
class FetchWithDataStateViewModel @Inject constructor(private val useCase: FetchWithDataStateUseCaseInterface) : ViewModel() {

    private val disposeBag = DisposeBag()

    val data: CompanyInfoViewData
        get() = CompanyInfoViewData(
            companyNameJP = useCase.companyInfo.map { it.nameJP },
            companyNameEN = useCase.companyInfo.map { it.nameEN },
            address = useCase.companyInfo.map { it.address },
            foundationDate = useCase.companyInfo.map { it.foundationDate.localizedExpression },
            capital = useCase.companyInfo.map { it.capital.localizedExpression },
            numberOfEmployees = useCase.companyInfo.map { "${it.numberOfEmployees}名" },
            visibilityOfProgress = visibilityOfProgress
        )

    private val visibilityOfProgress: Observable<Int>
        get() = useCase.isInProgress
            .map {
                if (it) View.VISIBLE else View.GONE
            }
            .distinctUntilChanged()
            .startWith(View.GONE)

    override fun onCleared() {
        super.onCleared()
        disposeBag.clear()
        useCase.dispose()
    }

    fun didTapFetchButton(id: Int) {
        useCase.fetch(id)
    }
}
