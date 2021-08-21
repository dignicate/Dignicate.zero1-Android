package com.dignicate.zero1.ui.subject01.case101.hiltdi

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.dignicate.zero1.rx.DisposeBag
import com.dignicate.zero1.rx.RxExtensions.disposedBy
import dagger.hilt.android.AndroidEntryPoint
import timber.log.Timber

@AndroidEntryPoint
class BasicFetchApiActivity : ComponentActivity() {

    private val disposeBag = DisposeBag()

    private val viewModel: BasicFetchApiViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setupBinding()
        setContent {
            Content {
                viewModel.didTapFetchButton(123)
            }
        }
    }

    private fun setupBinding() {
        viewModel.companyNameJP
            .subscribe {
                Timber.d("companyNameJP: $it")
            }
            .disposedBy(disposeBag)
    }
}

@Composable
private fun Content(didTapFetchButton: () -> Unit) {
    Column(
        modifier = Modifier.fillMaxWidth().padding(1.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Item()
        Button(
            onClick = {
                didTapFetchButton.invoke()
            }
        ) {
            Text("Fetch")
        }
    }
}

@Composable
private fun Item() {
    Row(Modifier.padding(1.dp)) {
        Text(
            text = "Test"
        )
        Text(
            text = "Value"
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun Preview() {
    Content {}
}
