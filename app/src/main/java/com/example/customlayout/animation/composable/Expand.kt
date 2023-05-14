package com.example.customlayout.animation.composable

import androidx.compose.animation.core.tween
import androidx.compose.animation.expandIn
import androidx.compose.animation.shrinkOut
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import com.example.customlayout.animation.model.AnimationScreenModel.Companion.ANIMATION_DURATION

@Composable
fun Expand() {
    AnimationVisibilityContainer(
        enter = expandIn(
            animationSpec = tween(ANIMATION_DURATION)
        ),
        exit = shrinkOut(
            animationSpec = tween(ANIMATION_DURATION),
            shrinkTowards = Alignment.BottomEnd
        )
    )
}