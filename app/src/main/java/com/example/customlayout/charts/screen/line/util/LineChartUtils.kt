package com.example.customlayout.charts.screen.line.util

import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Path
import com.example.customlayout.charts.screen.line.data.LineChartData
import com.example.customlayout.charts.screen.line.data.LineChartPoint

fun computeDrawableArea(
    xAxisDrawableArea: Rect,
    yAxisDrawableArea: Rect,
    size: Size,
    offset: Float
): Rect {
    val horizontalOffset = xAxisDrawableArea.width * offset / 100

    return Rect(
        left = yAxisDrawableArea.right + horizontalOffset,
        top = 0f,
        bottom = xAxisDrawableArea.top,
        right = size.width - horizontalOffset
    )
}

fun computeXAxisDrawableArea(yAxisWidth: Float, labelHeight: Float, size: Size): Rect {
    val top = size.height - labelHeight

    return Rect(yAxisWidth, top, size.width, size.height)
}

fun computeXAxisLabelsDrawableArea(xAxisDrawableArea: Rect, offset: Float): Rect {
    val horizontalOffset = xAxisDrawableArea.width * offset / 100

    return Rect(
        xAxisDrawableArea.left + horizontalOffset,
        xAxisDrawableArea.top,
        xAxisDrawableArea.right - horizontalOffset,
        xAxisDrawableArea.bottom
    )
}

fun computeYAxisDrawableArea(
    xAxisLabelSize: Float,
    size: Size
): Rect {
    val right = size.width / 10
    return Rect(0f, 0f, right, size.height - xAxisLabelSize)
}

fun computePointLocation(
    drawableArea: Rect,
    lineChartData: LineChartData,
    point: LineChartPoint,
    index: Int
): Offset {
    val x = index.toFloat() / (lineChartData.points.size - 1)
    val y = point.value / lineChartData.maxY

    return Offset(
        x * drawableArea.width + drawableArea.left,
        drawableArea.height - y * drawableArea.height
    )
}

fun drawWithProgress(
    index: Int,
    lineChartData: LineChartData,
    progress: Float,
    progressListener: (Float) -> Unit
) {
    val size = lineChartData.points.size
    val toIndex = (size * progress).toInt() + 1

    if (index == toIndex) {
        val sizeF = lineChartData.points.size.toFloat()
        val divider = 1f / sizeF
        val down = (index - 1) * divider
        progressListener((progress - down) / divider)
    } else if (index < toIndex) {
        progressListener(1f)
    }
}


fun computeLinePath(
    drawableArea: Rect,
    lineChartData: LineChartData,
    progress: Float
): Path = Path().apply {
    var pointLocationOffset: Offset? = null

    lineChartData.points.forEachIndexed { index, lineChartPoint ->
        drawWithProgress(index, lineChartData, progress) { progress ->
            val pointLocation =
                computePointLocation(drawableArea, lineChartData, lineChartPoint, index)

            if (index == 0) {
                moveTo(pointLocation.x, pointLocation.y)
            } else {
                if (progress < 1f) {
                    val xOffset = pointLocationOffset?.x ?: 0f
                    val yOffset = pointLocationOffset?.y ?: 0f

                    val x = (pointLocation.x - xOffset) * progress + xOffset
                    val y = (pointLocation.y - yOffset) * progress + yOffset
                    lineTo(x, y)
                } else {
                    lineTo(pointLocation.x, pointLocation.y)
                }
            }
            pointLocationOffset = pointLocation
        }
    }
}