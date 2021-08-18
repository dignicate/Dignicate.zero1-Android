package com.dignicate.zero1.ui.main

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModelProvider

class ComposeMainActivity : ComponentActivity() {

    private lateinit var viewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)
        setContent {
            Content(MainViewModel.ContentStructure.rowStates)
        }
    }
}

@Composable
private fun Content(menus: List<MainViewModel.RowState>) {
    Column(Modifier.padding(1.dp)) {
        menus.forEach {
            when (it) {
                is MainViewModel.RowState.SectionRow -> {
                    Header(it.section!!.title)
                }
                is MainViewModel.RowState.ItemRow -> {
                    Item(it.indexedItem!!.second, it.indexedItem!!.first.title)
                }
            }
        }
    }
}

@Composable
private fun Header(title: String) {
    Text(
        text = title,
        modifier = Modifier.fillMaxWidth()
            .padding(horizontal = 16.dp)
            .padding(top = 24.dp)
            .padding(bottom = 12.dp)
    )
}

@Composable
private fun Item(number: Int, title: String) {
    Row(verticalAlignment = Alignment.CenterVertically) {
        Text(
            text = "${number + 1}",
            style = TextStyle(
                textAlign = TextAlign.Center,
            ),
            modifier = Modifier.width(32.dp).height(32.dp).padding(top = 8.dp)
        )
        Text(text = title, modifier = Modifier.fillMaxWidth().height(32.dp).padding(top = 8.dp))
    }
}

@Preview(showBackground = true)
@Composable
private fun Preview() {
    Content(MainViewModel.ContentStructure.rowStates)
}
