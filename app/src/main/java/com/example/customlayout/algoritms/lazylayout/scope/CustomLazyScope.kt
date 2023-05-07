package com.example.customlayout.algoritms.lazylayout.scope

import com.example.customlayout.algoritms.data.ComposableItemContent
import com.example.customlayout.algoritms.lazylayout.data.LazyListItem

interface CustomLazyScope {
    fun items(items: List<LazyListItem>, itemContent: ComposableItemContent)
}