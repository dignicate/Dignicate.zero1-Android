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
            Content(viewModel)
        }
    }
}

@Composable
private fun Content(viewModel: BasicFetchApiViewModel) {
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
                Item("和名", viewModel.companyNameJP)
                Item("英名", viewModel.companyNameEN)
                Row(
                    verticalAlignment = Alignment.Bottom,
                    modifier = Modifier.fillMaxHeight()
                ) {
                    Button(
                        onClick = {
                            viewModel.didTapFetchButton(123)
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

//@Preview(showBackground = true)
//@Composable
//private fun Preview() {
//    Content {}
//}
