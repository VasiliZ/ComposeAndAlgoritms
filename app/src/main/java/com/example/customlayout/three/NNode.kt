package com.example.customlayout.three

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf

data class NNode(
    val value: Int,
    val isChecked: MutableState<Boolean> = mutableStateOf(false),
    var listChildren: MutableList<NNode?> = mutableListOf()
)
