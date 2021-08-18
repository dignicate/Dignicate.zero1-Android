package com.dignicate.zero1.ui.hiltdi

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import dagger.hilt.android.AndroidEntryPoint
import com.dignicate.zero1.ui.manualdi.MenuDefinition.ContentStructure
import com.dignicate.zero1.ui.manualdi.MenuDefinition.RowState
import com.dignicate.zero1.ui.manualdi.MenuDefinition.Item

@AndroidEntryPoint
class ComposeHiltMainActivity : ComponentActivity() {

    private val viewModel: ComposeHiltMainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Content(viewModel.rowStates) {
                when (it) {
                    Item.BASIC_FETCH -> {

                    }
                }
            }
        }
    }
}

@Composable
private fun Content(menus: List<RowState>, onClick: (Item) -> Unit) {
    Column(Modifier.padding(1.dp)) {
        menus.forEach {
            when (it) {
                is RowState.SectionRow -> {
                    Header(it.section!!.title)
                }
                is RowState.ItemRow -> {
                    val item = it.indexedItem!!.first
                    Item(it.indexedItem!!.second, item.title, onClick = { onClick.invoke(item) })
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
            .padding(horizontal = 8.dp)
            .padding(top = 24.dp)
            .padding(bottom = 12.dp)
    )
}

@Composable
private fun Item(number: Int, title: String, onClick: () -> Unit) {
    Row(verticalAlignment = Alignment.CenterVertically) {
        Text(
            text = "${number + 1}",
            style = TextStyle(
                textAlign = TextAlign.Center,
            ),
            modifier = Modifier
                .width(40.dp)
                .height(40.dp)
                .padding(top = 8.dp)
        )
        ClickableText(
            text = AnnotatedString(title),
            modifier = Modifier
                .fillMaxWidth()
                .height(40.dp)
                .padding(top = 8.dp),
            onClick = { onClick.invoke() }
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun Preview() {
    Content(ContentStructure.rowStates, onClick = {})
}
