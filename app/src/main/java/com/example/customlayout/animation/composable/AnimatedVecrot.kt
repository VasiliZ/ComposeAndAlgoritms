package com.example.customlayout.animation.composable

import androidx.compose.animation.graphics.ExperimentalAnimationGraphicsApi
import androidx.compose.animation.graphics.res.animatedVectorResource
import androidx.compose.animation.graphics.res.rememberAnimatedVectorPainter
import androidx.compose.animation.graphics.vector.AnimatedImageVector
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import com.example.customlayout.R

@OptIn(ExperimentalAnimationGraphicsApi::class)
@Composable
fun AnimatedVector() {
    val vector = AnimatedImageVector.animatedVectorResource(id = R.drawable.avd_anim)
    var atEnd by remember {
        mutableStateOf(false)
    }

    Image(
        modifier = Modifier
            .clickable(
                indication = null,
                interactionSource = MutableInteractionSource()
            ) {
                atEnd = !atEnd
            },
        painter = rememberAnimatedVectorPainter(animatedImageVector = vector, atEnd = atEnd),
        contentDescription = null,
        contentScale = ContentScale.Crop
    )
}