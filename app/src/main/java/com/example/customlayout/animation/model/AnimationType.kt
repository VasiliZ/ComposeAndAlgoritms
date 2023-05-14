package com.example.customlayout.animation.model

import androidx.compose.runtime.Composable
import com.example.customlayout.R
import com.example.customlayout.animation.composable.AnimatedVector
import com.example.customlayout.animation.composable.AnimationContent
import com.example.customlayout.animation.composable.Expand
import com.example.customlayout.animation.composable.FadeIn
import com.example.customlayout.animation.composable.Scale
import com.example.customlayout.animation.composable.Slide

enum class AnimationType(val resTitleId: Int, val View: @Composable () -> Unit) {
    CONTENT(R.string.animation_content, { AnimationContent() }),
    FADE(R.string.animation_fade, { FadeIn() }),
    SLIDE(R.string.animation_slide, { Slide() }),
    SCALE(R.string.animation_scale, { Scale() }),
    EXPAND(R.string.animation_expand, { Expand() }),
    VECTOR(R.string.animation_vector, { AnimatedVector() }),
}