package com.example.customlayout.animation.composable

import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.runtime.Composable
import com.example.customlayout.animation.model.AnimationScreenModel

@Composable
fun FadeIn() {
    AnimationVisibilityContainer(
        enter = fadeIn(
            initialAlpha = 0.4f,
            animationSpec = tween(AnimationScreenModel.ANIMATION_DURATION)
        ),
        exit = fadeOut(
            animationSpec = tween(AnimationScreenModel.ANIMATION_DURATION)
        )
    )
}