package com.example.customlayout.animation.composable

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.spring
import androidx.compose.animation.core.tween
import androidx.compose.animation.scaleIn
import androidx.compose.animation.scaleOut
import androidx.compose.runtime.Composable
import com.example.customlayout.animation.model.AnimationScreenModel.Companion.ANIMATION_DURATION

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun Scale() {
    AnimationVisibilityContainer(
        enter = scaleIn(
            animationSpec = spring(),
            initialScale = 0f,
        ),
        exit = scaleOut(
            animationSpec = tween(ANIMATION_DURATION),
            targetScale = 0f
        )
    )
}