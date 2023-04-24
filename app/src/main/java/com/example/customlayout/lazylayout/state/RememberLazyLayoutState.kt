package com.example.customlayout.lazylayout.state

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember

@Composable
fun rememberLazyLayoutState(): LazyLayoutState {
    return remember {
        LazyLayoutState()
    }
}