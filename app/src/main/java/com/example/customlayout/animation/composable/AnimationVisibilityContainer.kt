package com.example.customlayout.animation.composable

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.EnterExitState
import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.animateColor
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun AnimationVisibilityContainer(
    enter: EnterTransition,
    exit: ExitTransition
) {
    var visible by remember {
        mutableStateOf(true)
    }

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        StartAnimationButton {
            visible = !visible
        }
        Spacer(modifier = Modifier.height(32.dp))
        AnimatedVisibility(
            visible = visible,
            enter = enter,
            exit = exit
        ) {
            val background by transition.animateColor(label = "color") {
                if (it == EnterExitState.Visible) {
                    Color.Blue
                } else {
                    Color.Red
                }
            }
            AnimatedView(background)
        }
    }
}