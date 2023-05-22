package com.example.customlayout.animation.composable

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.unit.dp
import com.example.customlayout.TestTags.ANIMATED_VIEW_TAG

@Composable
fun AnimatedView(background: Color = Color.Blue, text: String = "") {
    Box(
        modifier = Modifier
            .size(100.dp)
            .background(
                if (text.isNotBlank()) Color.Transparent else background,
                shape = CircleShape
            )
            .testTag(ANIMATED_VIEW_TAG),
        contentAlignment = Alignment.Center
    ) {
        if (text.isNotBlank()) {
            Text(text = text, color = Color.Black)
        }
    }
}