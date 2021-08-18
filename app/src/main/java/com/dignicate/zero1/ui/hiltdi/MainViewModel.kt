package com.dignicate.zero1.ui.hiltdi

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import com.dignicate.zero1.ui.manualdi.MenuDefinition.ContentStructure

@HiltViewModel
class MainViewModel @Inject constructor(
) : ViewModel() {

    val rowStates = ContentStructure.rowStates
}