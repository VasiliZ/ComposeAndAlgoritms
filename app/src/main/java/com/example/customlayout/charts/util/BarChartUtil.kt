package com.example.customlayout.charts.util

import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.drawscope.DrawScope
import com.example.customlayout.charts.screen.bar.data.Bar
import com.example.customlayout.charts.screen.bar.data.BarChartData

fun axisArea(
    drawScope: DrawScope,
    totalSize: Size,
    xAxisDrawer: XAxisDrawer,
    labelDrawer: LabelDrawer
): Pair<Rect, Rect> {
    with(drawScope) {
        val yAxisTop = labelDrawer.getLabelOffset(this)
        val yAxisRight = size.width / 10f
        val xAxisRight = totalSize.width
        val xAxisTop = totalSize.height - xAxisDrawer.requireHeight(this)

        return Rect(
            yAxisRight, xAxisTop, xAxisRight, totalSize.height
        ) to Rect(
            0F, yAxisTop, yAxisRight, xAxisTop
        )
    }
}

fun barDrawableArea(axisArea: Rect): Rect {
    return Rect(
        axisArea.left,
        0F,
        axisArea.right,
        axisArea.top
    )
}

fun BarChartData.forEachWithArea(
    drawScope: DrawScope,
    barDrawableArea: Rect,
    progress: Float,
    labelDrawer: LabelDrawer,
    block: (area: Rect, bar: Bar) -> Unit
) {
    val barCount = barList.size
    val widthBarArea = barDrawableArea.width / barCount
    val barOffset = widthBarArea * 0.2f

    barList.forEachIndexed { index, bar ->

        val left = barDrawableArea.left + index * widthBarArea
        val height = barDrawableArea.height
        val barHeight = (height - labelDrawer.getLabelOffset(drawScope)) * progress
        val barRect = Rect(
            left = left + barOffset,
            top = barDrawableArea.bottom - bar.value / maxYValue * barHeight,
            right = left + widthBarArea - barOffset,
            bottom = barDrawableArea.bottom
        )

        block(barRect, bar)
    }
}