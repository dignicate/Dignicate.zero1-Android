package com.dignicate.zero1.ui.subject01.case101.hiltdi

import androidx.activity.ComponentActivity
import androidx.activity.viewModels
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class BasicFetchApiActivity : ComponentActivity() {

    private val viewModel: BasicFetchApiViewModel by viewModels()

}