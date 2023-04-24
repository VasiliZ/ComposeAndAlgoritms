package com.example.customlayout.lazylayout.scope

import com.example.customlayout.data.ComposableItemContent
import com.example.customlayout.lazylayout.data.LazyListItem
import com.example.customlayout.lazylayout.data.LazyListItemContent

class CustomLazyListScopeImpl : CustomLazyScope {

    private var _items = mutableListOf<LazyListItemContent>()
    val items: List<LazyListItemContent> = _items

    override fun items(items: List<LazyListItem>, itemContent: ComposableItemContent) {
        items.forEach {
            _items.add(LazyListItemContent(it, itemContent))
        }
    }
}