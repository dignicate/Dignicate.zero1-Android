package com.dignicate.zero1.ui.subject01.case101.hiltdi

import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rxjava2.subscribeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.dignicate.zero1.ui.theme.Dignicatezero1Theme
import io.reactivex.Observable

object Composables {
    @Composable
    fun Content(data: CompanyInfoViewData, didTapFetchButton: () -> Unit) {
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
            val visible: Boolean by data.visible.subscribeAsState(initial = false)
            if (visible) {
                Column(
                    modifier = Modifier.fillMaxSize(),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Bottom
                ) {
                    CircularProgressIndicator(
                        modifier = Modifier.padding(bottom = 128.dp)
                    )
                }
            }
        }
    }

    @Composable
    fun Item(title: String, observable: Observable<String>) {
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
}
