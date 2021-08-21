package com.dignicate.zero1.ui.hiltdi

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material.Divider
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.dignicate.zero1.ui.manualdi.MenuDefinition
import com.dignicate.zero1.ui.subject01.case101.hiltdi.BasicFetchApiActivity
import com.dignicate.zero1.ui.theme.Gray
import com.dignicate.zero1.ui.theme.LightGray
import com.dignicate.zero1.ui.theme.Purple700

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
                            startActivity(Intent(requireContext(), BasicFetchApiActivity::class.java))
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
                    Item(
                        it.indexedItem!!.second,
                        item.title,
                        item.isHiltAvailable,
                        onClick = { onClick.invoke(item) })
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
            .padding(bottom = 12.dp),
        style = TextStyle(
            fontSize = 16.sp,
            color = Purple700
        )
    )
}

@Composable
private fun Item(number: Int, title: String, isAvailable: Boolean, onClick: () -> Unit) {
    Column {
        Row {
            Row(
                verticalAlignment = Alignment.Bottom,
                modifier = Modifier
                    .width(40.dp)
                    .height(40.dp)
                    .background(color = LightGray)
            ) {
                Text(
                    text = "${number + 1}",
                    modifier = Modifier.width(40.dp).height(32.dp),
                    style = TextStyle(
                        textAlign = TextAlign.Center,
                        color = if (isAvailable) Color.Black else Color.Gray
                    )
                )
            }
            Row(
                verticalAlignment = Alignment.Bottom,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(40.dp)
                    .background(color = if (isAvailable) Color.White else LightGray)
            ) {
                ClickableText(
                    text = AnnotatedString(title),
                    style = TextStyle(
                        color = if (isAvailable) Color.Black else Color.Gray
                    ),
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(40.dp)
                        .padding(start = 8.dp)
                        .padding(top = 8.dp),
                    onClick = { onClick.invoke() }
                )
            }
        }
        Divider(color = Gray, thickness = 1.dp)
    }

}

@Preview(showBackground = true)
@Composable
private fun Preview() {
    Content(MenuDefinition.ContentStructure.rowStates, onClick = {})
}
