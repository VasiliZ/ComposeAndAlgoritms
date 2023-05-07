package com.example.customlayout.algoritms.three

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf

data class Node(
    val value: Int,
    val isChecked: MutableState<Boolean> = mutableStateOf(false),
    var leftChild: Node? = null,
    var rightChild: Node? = null
)
