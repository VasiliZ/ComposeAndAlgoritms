package com.example.customlayout.algoritms.composable

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun RecursionItem(data: String) {
    val shape = RoundedCornerShape(12.dp)
    Box(
        modifier = Modifier
            .padding(4.dp)
            .fillMaxWidth()
            .wrapContentHeight()
            .border(border = BorderStroke(1.dp, Color.Black), shape = shape)
            .shadow(
                elevation = 4.dp,
                shape = shape,
                spotColor = Color.Red
            )
            .background(Color.LightGray, shape),
        contentAlignment = Alignment.Center
    ) {
        Text(text = data)
    }
}