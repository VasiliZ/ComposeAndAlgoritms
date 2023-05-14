package com.example.customlayout.animation.composable

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.AnimatedContentScope
import androidx.compose.animation.ContentTransform
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun <T> AnimationContentContainer(
    targetState: T,
    transitionSpec: AnimatedContentScope<T>.() -> ContentTransform,
    startAnimationCallback: () -> Unit
) {

    Column(Modifier.fillMaxSize(), horizontalAlignment = Alignment.CenterHorizontally) {

        StartAnimationButton {
            startAnimationCallback()
        }

        Spacer(modifier = Modifier.height(height = 32.dp))

        AnimatedContent(
            targetState = targetState,
            label = "someContent",
            transitionSpec = transitionSpec
        ) {
            AnimatedView(text = it.toString())
        }
    }
}