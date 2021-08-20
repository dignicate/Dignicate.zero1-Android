package com.dignicate.zero1.ui.hiltdi

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.dignicate.zero1.ui.manualdi.MenuDefinition

class MainFragment : Fragment() {

    companion object {
        fun newInstance() = MainFragment()
    }

    private lateinit var viewModel: MainViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)
        return ComposeView(requireContext()).apply {
            setContent {
                Content(viewModel.rowStates) {
                    when (it) {
                        MenuDefinition.Item.BASIC_FETCH -> {

                        }
                    }
                }
            }
        }
    }
}

@Composable
private fun Content(menus: List<MenuDefinition.RowState>, onClick: (MenuDefinition.Item) -> Unit) {
    Column(Modifier.padding(1.dp)) {
        menus.forEach {
            when (it) {
                is MenuDefinition.RowState.SectionRow -> {
                    Header(it.section!!.title)
                }
                is MenuDefinition.RowState.ItemRow -> {
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
    Content(MenuDefinition.ContentStructure.rowStates, onClick = {})
}
