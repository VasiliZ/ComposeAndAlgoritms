package com.example.customlayout.lazylayout.contentprovider

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.lazy.layout.LazyLayoutItemProvider
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import com.example.customlayout.lazylayout.data.LazyItemViewBoundaries
import com.example.customlayout.lazylayout.data.LazyListItem
import com.example.customlayout.lazylayout.data.LazyListItemContent

@OptIn(ExperimentalFoundationApi::class)
class LazyListItemProvider(
    private val itemState: State<List<LazyListItemContent>>
) : LazyLayoutItemProvider {

    override val itemCount
        get() = itemState.value.size

    @Composable
    override fun Item(index: Int) {
        val item = itemState.value.getOrNull(index)

        item?.itemContent?.invoke(item.item)
    }

    fun getItem(index: Int): LazyListItem? {
        return itemState.value.getOrNull(index)?.item
    }

    fun getItemIndexesInRange(boundaries: LazyItemViewBoundaries): List<Int> {
        val result = mutableListOf<Int>()

        itemState.value.forEachIndexed { index, lazyListItemContent ->
            val listItem = lazyListItemContent.item
            if (listItem.x in boundaries.fromX..boundaries.toX
                && listItem.y in boundaries.fromY..boundaries.toY
            ) {
                result.add(index)
            }
        }

        return result
    }
}