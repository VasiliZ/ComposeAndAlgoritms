package com.example.customlayout.animation.composable

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.SizeTransform
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.animation.with
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun AnimationContent() {
    var state by remember {
        mutableStateOf(0)
    }

    AnimationContentContainer(
        targetState = state,
        startAnimationCallback = { state++ },
        transitionSpec = {
            (slideInVertically() + fadeIn() with slideOutVertically() + fadeOut())
                .using(SizeTransform(clip = false))
        })
}