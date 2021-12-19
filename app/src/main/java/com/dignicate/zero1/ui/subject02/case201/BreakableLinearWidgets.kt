package com.dignicate.zero1.ui.subject02.case201

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.material.Text
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.sp
import com.dignicate.zero1.ui.theme.Dignicatezero1Theme

object BreakableLinearWidgets {

    @Composable
    fun Container(tags: List<String>) {
        Dignicatezero1Theme {
            Row(
                modifier = Modifier
                    .width(240.dp)
                ,
                verticalAlignment = Alignment.Top
            ) {
                tags.forEach {
                    Box(
                        modifier = Modifier
                            .padding(horizontal = 1.dp, vertical = 2.dp)
                            .background(Color.Black)
                    ) {
                        Text(
                            text = it,
                            color = Color.White,
                            fontSize = 12.sp,
                            modifier = Modifier.padding(horizontal = 1.dp, vertical = 2.dp)
                        )
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun Preview() {
    BreakableLinearWidgets.Container(
        tags = listOf("古典", "経済学", "宇宙物理学", "世界史", "シンギュラリティー", "80億年後の地球",  "ソマリア", "どうぶつの森", "ローマ帝国")
    )
}
