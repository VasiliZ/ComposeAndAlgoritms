package com.example.customlayout.algoritms.composable

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.customlayout.algoritms.three.NNode

@Composable
fun NNodeThree(nNodeThree: NNode?) {

    Column(
        modifier = Modifier
            .wrapContentHeight(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box(
            modifier = Modifier
                .wrapContentWidth()
                .wrapContentHeight()
                .padding(8.dp)
                .background(
                    color = if (nNodeThree?.isChecked?.value == true) {
                        Color.Red
                    } else
                        Color.Cyan, shape = CircleShape
                )
                .size(30.dp)
        ) {
            Text(
                text = nNodeThree?.value?.toString() ?: "",
                textAlign = TextAlign.Center,
                modifier = Modifier.align(
                    Alignment.Center
                )
            )
        }

        Row(
            modifier = Modifier
                .wrapContentWidth()
                .wrapContentHeight()
                .padding(end = 16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            nNodeThree?.listChildren?.forEach {
                if (it != null) {
                    Column {
                        Box(modifier = Modifier.height(50.dp))
                        NNodeThree(nNodeThree = it)
                    }
                }
            }
        }
    }
}