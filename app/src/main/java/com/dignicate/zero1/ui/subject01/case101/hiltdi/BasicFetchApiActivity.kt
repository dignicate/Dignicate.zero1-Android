package com.dignicate.zero1.ui.subject01.case101.hiltdi

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rxjava2.subscribeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.dignicate.zero1.ui.theme.Dignicatezero1Theme
import dagger.hilt.android.AndroidEntryPoint
import io.reactivex.Observable

@AndroidEntryPoint
class BasicFetchApiActivity : ComponentActivity() {

    private val viewModel: BasicFetchApiViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Content(viewModel.data) {
                viewModel.didTapFetchButton(1234)
            }
        }
    }
}

@Composable
private fun Content(data: BasicFetchApiViewModel.Data, didTapFetchButton: () -> Unit) {
    Dignicatezero1Theme {
        Box {
            Column(
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "企業情報",
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 32.dp)
                        .padding(start = 24.dp),
                    textAlign = TextAlign.Start
                )
                Item("和名", data.companyNameJP)
                Item("英名", data.companyNameEN)
                Item("所在地", data.address)
                Item("設立日", data.foundationDate)
                Item("資本金", data.capital)
                Item("従業員数", data.numberOfEmployees)
                Row(
                    verticalAlignment = Alignment.Bottom,
                    modifier = Modifier.fillMaxHeight()
                ) {
                    Button(
                        onClick = {
                            didTapFetchButton.invoke()
                        },
                        modifier = Modifier.padding(bottom = 32.dp)
                    ) {
                        Text("Fetch")
                    }
                }
            }
        }
    }
}

@Composable
private fun Item(title: String, observable: Observable<String>) {
    Row(
        Modifier
            .padding(horizontal = 24.dp)
            .padding(bottom = 8.dp)
    ) {
        val value: String by observable.subscribeAsState("")
        Text(
            text = title,
            modifier = Modifier.width(108.dp)
        )
        Text(
            text = value,
            modifier = Modifier.fillMaxWidth()
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun Preview() {
    Content(
        BasicFetchApiViewModel.Data(
            companyNameJP = Observable.just("name JP"),
            companyNameEN = Observable.just("name EN"),
            address = Observable.just(""),
            foundationDate = Observable.just(""),
            capital = Observable.just(""),
            numberOfEmployees = Observable.just("")
        )
    ) {}
}
