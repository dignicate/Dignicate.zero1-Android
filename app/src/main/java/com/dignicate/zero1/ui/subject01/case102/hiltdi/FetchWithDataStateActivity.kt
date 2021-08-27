package com.dignicate.zero1.ui.subject01.case102.hiltdi

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import com.dignicate.zero1.ui.subject01.case101.hiltdi.Composables.Content
import dagger.hilt.android.AndroidEntryPoint

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
