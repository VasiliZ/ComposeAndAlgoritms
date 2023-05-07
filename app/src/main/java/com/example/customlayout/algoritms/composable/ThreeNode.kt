package com.example.customlayout.algoritms.composable

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.customlayout.algoritms.three.Node

private const val LAYOUT_DIVIDER = 4f

@Composable
fun ThreeNode(value: Node?) {

    val layoutWidth = remember {
        mutableStateOf(0)
    }

    Column(
        modifier = Modifier
            .wrapContentHeight(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Box(
            modifier = Modifier
                .wrapContentWidth()
                .wrapContentHeight()
                .background(
                    color = if (value?.isChecked?.value == true) {
                        Color.Red
                    } else Color.Cyan, shape = CircleShape
                )
                .size(30.dp)
        ) {
            Text(
                text = value?.value?.toString() ?: "",
                textAlign = TextAlign.Center,
                modifier = Modifier.align(
                    Alignment.Center
                )
            )
        }

        Box(modifier = Modifier
            .height(50.dp)
            .drawBehind {
                if (value?.leftChild != null) {
                    drawLine(
                        color = Color.Black,
                        start = Offset.Zero,
                        end = Offset(-layoutWidth.value / LAYOUT_DIVIDER, size.height)
                    )
                }

                if (value?.rightChild != null) {
                    drawLine(
                        color = Color.Black,
                        start = Offset.Zero,
                        end = Offset(layoutWidth.value / LAYOUT_DIVIDER, size.height)
                    )
                }
            }) {}
        Row(
            modifier = Modifier
                .wrapContentWidth()
                .wrapContentHeight()
                .onGloballyPositioned {
                    layoutWidth.value = it.size.width
                },
        ) {
            if (value?.leftChild != null) {
                ThreeNode(value = value.leftChild)
            }

            Spacer(modifier = Modifier.width(20.dp))

            if (value?.rightChild != null) {
                ThreeNode(value = value.rightChild)
            }
        }
    }
}