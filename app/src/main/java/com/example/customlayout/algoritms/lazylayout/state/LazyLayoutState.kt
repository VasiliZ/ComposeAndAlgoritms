package com.example.customlayout.algoritms.lazylayout.state

import androidx.compose.runtime.Stable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.unit.Constraints
import androidx.compose.ui.unit.IntOffset
import com.example.customlayout.algoritms.lazylayout.data.LazyItemViewBoundaries

@Stable
class LazyLayoutState {

    private val _offsetState = mutableStateOf(IntOffset(0, 0))
    val offsetState = _offsetState

    fun onDrag(offset: IntOffset) {
        val x = (_offsetState.value.x - offset.x).coerceAtLeast(0)
        val y = (_offsetState.value.y - offset.y).coerceAtLeast(0)
        _offsetState.value = IntOffset(x, y)
    }

    fun getBoundaries(
        constraints: Constraints,
        threshold: Int = 500
    ): LazyItemViewBoundaries {
        return LazyItemViewBoundaries(
            fromX = offsetState.value.x - threshold,
            toX = constraints.maxWidth + offsetState.value.x + threshold,
            fromY = offsetState.value.y - threshold,
            toY = constraints.maxHeight + offsetState.value.y + threshold
        )
    }
}