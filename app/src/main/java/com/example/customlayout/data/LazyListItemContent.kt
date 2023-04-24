package com.example.customlayout.data

import androidx.compose.runtime.Composable
import com.example.customlayout.lazylayout.data.LazyListItem

typealias ComposableItemContent = @Composable (LazyListItem) -> Unit