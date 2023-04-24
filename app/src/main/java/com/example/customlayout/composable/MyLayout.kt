package com.example.customlayout.composable

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.Layout

@Composable
fun MyLayout(
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit
) {

    Layout(modifier = modifier, content = content) { measurebles, constraints ->
        val placebles = measurebles.map {
            it.measure(constraints)
        }

        val maxHeight = placebles.maxOf { it.height }

        layout(placebles.sumOf { it.width }, maxHeight) {
            var xPosition = 0

            placebles.forEach { placeable ->
                placeable.placeRelative(y = maxHeight - placeable.height, x = xPosition)
                xPosition += placeable.width
            }
        }
    }
}