package com.example.customlayout.algoritms.lazylayout.data

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf

data class LazyListItem(
    val value: MutableState<Int> = mutableStateOf(0),


    // coordinates for rendering in custom lazy layout scope
    // for each item
    val x: Int = 0,
    val y: Int = 0,
)
