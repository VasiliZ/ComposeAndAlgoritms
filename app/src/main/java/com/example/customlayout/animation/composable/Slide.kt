package com.example.customlayout.animation.composable

import androidx.compose.animation.core.tween
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalView
import com.example.customlayout.animation.model.AnimationScreenModel.Companion.ANIMATION_DURATION

@Composable
fun Slide() {
    val width = LocalView.current.width
    AnimationVisibilityContainer(
        enter = slideInHorizontally(
            animationSpec = tween(ANIMATION_DURATION),
            initialOffsetX = { -(width / 2) }
        ),
        exit = slideOutHorizontally(
            animationSpec = tween(ANIMATION_DURATION),
            targetOffsetX = { width }
        ))
}