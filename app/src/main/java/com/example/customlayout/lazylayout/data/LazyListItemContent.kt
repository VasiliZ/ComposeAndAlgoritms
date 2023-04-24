package com.example.customlayout.lazylayout.data

import com.example.customlayout.data.ComposableItemContent

data class LazyListItemContent(
    val item: LazyListItem,
    var itemContent: ComposableItemContent
)