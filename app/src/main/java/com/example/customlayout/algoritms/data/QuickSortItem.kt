package com.example.customlayout.algoritms.data

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf

data class QuickSortItem(
    val value: MutableState<Int>,
    var isPivot: MutableState<Boolean> = mutableStateOf(false)
)
