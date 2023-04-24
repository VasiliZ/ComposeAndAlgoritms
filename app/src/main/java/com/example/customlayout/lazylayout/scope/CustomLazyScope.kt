package com.example.customlayout.lazylayout.scope

import com.example.customlayout.data.ComposableItemContent
import com.example.customlayout.lazylayout.data.LazyListItem

interface CustomLazyScope {
    fun items(items: List<LazyListItem>, itemContent: ComposableItemContent)
}