package com.example.customlayout.algoritms.lazylayout

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.foundation.lazy.layout.LazyLayout
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clipToBounds
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.Placeable
import androidx.compose.ui.unit.Constraints
import androidx.compose.ui.unit.IntOffset
import com.example.customlayout.algoritms.lazylayout.data.LazyListItem
import com.example.customlayout.algoritms.lazylayout.scope.CustomLazyScope
import com.example.customlayout.algoritms.lazylayout.state.LazyLayoutState
import com.example.customlayout.algoritms.lazylayout.state.rememberLazyLayoutState

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun MyLazyLayout(
    modifier: Modifier = Modifier,
    state: LazyLayoutState = rememberLazyLayoutState(),
    content: CustomLazyScope.() -> Unit
) {
    val itemProvider = rememberItemProvider(customLazyListScope = content)

    LazyLayout(
        modifier = modifier
            .clipToBounds()
            .lazyLayoutPointerInput(state),
        itemProvider = itemProvider
    ) { constraints ->
        val boundaries = state.getBoundaries(constraints)
        val indexes = itemProvider.getItemIndexesInRange(boundaries = boundaries)

        val indexWithPlaces = indexes.associateWith {
            measure(it, Constraints())
        }
        val maxHeight = indexWithPlaces.flatMap {
            it.value
        }.maxOf {
            it.height
        }


        layout(constraints.maxWidth, maxHeight) {
            var xOffset = 0
            indexWithPlaces.forEach { (index, place) ->

                val item = itemProvider.getItem(index)
                item?.let {
                    placeItem(state, item, place, xOffset, maxHeight)
                }

                if (index < indexWithPlaces.size) {
                    xOffset += place[0].width
                }
            }
        }
    }
}

private fun Modifier.lazyLayoutPointerInput(state: LazyLayoutState): Modifier {
    return pointerInput(Unit) {
        detectDragGestures { change, dragAmount ->
            change.consume()
            state.onDrag(IntOffset(dragAmount.x.toInt(), 0))
        }
    }
}

private fun Placeable.PlacementScope.placeItem(
    state: LazyLayoutState,
    listItem: LazyListItem,
    placeables: List<Placeable>,
    xOffset: Int,
    maxItemHeight: Int
) {
    val xPosition = listItem.x - state.offsetState.value.x + xOffset

    placeables.forEach { placeable ->
        placeable.placeRelative(
            xPosition,
            maxItemHeight - placeable.height
        )
    }
}