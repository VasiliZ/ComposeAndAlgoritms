package com.example.customlayout.charts.screen.pie.util

import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Canvas
import androidx.compose.ui.graphics.Paint
import androidx.compose.ui.graphics.PaintingStyle
import com.example.customlayout.charts.screen.pie.data.PieChartSlice

class SliceDrawer(private val sliceWidth: Float) {

    private val paint by lazy {
        Paint().apply {
            isAntiAlias = true
            style = PaintingStyle.Stroke
        }
    }

    private fun computeSectorWidth(area: Size): Float {
        val minSize = area.width.coerceAtMost(area.height)
        //divided by 2 because view draws from 2 sides at the same time
        //and get percent by this value
        return sliceWidth * minSize / (2 * 100F)
    }

    private fun computeDrawableArea(area: Size, sliceWidth: Float): Rect {
        val sliceWidthOffset = sliceWidth / 2
        val horizontalOffset = (area.width - area.height) / 2F

        return Rect(
            left = sliceWidthOffset + horizontalOffset,
            top = sliceWidthOffset,
            right = area.width - sliceWidthOffset - horizontalOffset,
            bottom = area.height - sliceWidthOffset
        )
    }

    fun drawSlice(
        canvas: Canvas,
        area: Size,
        startAngle: Float,
        sweepAngle: Float,
        slice: PieChartSlice
    ) {
        val sliceWidth = computeSectorWidth(area)

        val drawableArea = computeDrawableArea(area, sliceWidth)

        canvas.drawArc(
            rect = drawableArea,
            paint = paint.apply {
                color = slice.color
                strokeWidth = sliceWidth
            },
            startAngle = startAngle,
            sweepAngle = sweepAngle,
            useCenter = false
        )
    }
}