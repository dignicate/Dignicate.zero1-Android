package com.dignicate.zero1.ui.subject01.case101.hiltdi

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.dignicate.zero1.rx.DisposeBag
import com.dignicate.zero1.ui.theme.Dignicatezero1Theme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class BasicFetchApiActivity : ComponentActivity() {

    private val disposeBag = DisposeBag()

    private val viewModel: BasicFetchApiViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setupBinding()
        setContent {
            Content(viewModel)
        }
    }

    private fun setupBinding() {

//        viewModel.companyNameJP
//            .subscribe {
//                Timber.d("companyNameJP: $it")
//            }
//            .disposedBy(disposeBag)
    }
}

@Composable
private fun Content(viewModel: BasicFetchApiViewModel) {
    Dignicatezero1Theme {
        BoxWithConstraints {
            Column(
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "企業情報",
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 32.dp)
                        .padding(start = 24.dp),
                    textAlign = TextAlign.Start
                )
                Item("和名")
                Item("英名")
                Column(
                    verticalArrangement = Arrangement.Bottom,
                    horizontalAlignment = Alignment.CenterHorizontally
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
private fun Item(title: String) {
    Row(Modifier.padding(24.dp)) {
        Text(
            text = title,
            modifier = Modifier.width(108.dp)
        )
        Text(
            text = "TODO (use mutable state here)",
            modifier = Modifier.fillMaxWidth()
        )
    }
}

//@Preview(showBackground = true)
//@Composable
//private fun Preview() {
//    Content {}
//}
