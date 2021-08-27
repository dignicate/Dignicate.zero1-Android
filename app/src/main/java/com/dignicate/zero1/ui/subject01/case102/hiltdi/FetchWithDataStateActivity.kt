package com.dignicate.zero1.ui.subject01.case102.hiltdi

import android.os.Bundle
import android.view.View
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.dignicate.zero1.ui.subject01.case101.hiltdi.CompanyInfoViewData
import com.dignicate.zero1.ui.subject01.case101.hiltdi.Composables.Content
import dagger.hilt.android.AndroidEntryPoint
import io.reactivex.Observable

@AndroidEntryPoint
class FetchWithDataStateActivity : ComponentActivity() {

    private val viewModel: FetchWithDataStateViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Content(viewModel.data) {
                viewModel.didTapFetchButton(1234)
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun Preview() {
    Content(
        CompanyInfoViewData(
            companyNameJP = Observable.just("name JP"),
            companyNameEN = Observable.just("name EN"),
            address = Observable.just(""),
            foundationDate = Observable.just(""),
            capital = Observable.just(""),
            numberOfEmployees = Observable.just(""),
            visibilityOfProgress = Observable.just(View.VISIBLE)
        )
    ) {}
}
