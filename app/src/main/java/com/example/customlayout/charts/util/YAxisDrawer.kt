package com.example.customlayout.charts.util

import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.graphics.Canvas
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Paint
import androidx.compose.ui.graphics.PaintingStyle
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.graphics.nativeCanvas
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlin.math.roundToInt
import android.graphics.Color as AndroidColor
import android.graphics.Paint as AndroidPaint
import android.graphics.Rect as AndroidRect

class YAxisDrawer(
    private val labelTextSize: TextUnit = 12.sp,
    private val labelTextColor: Int = AndroidColor.BLACK,
    private val drawLabelOffset: Int = 3,
    private val labelValueFormatter: LabelFormatter = { value -> "%.1f".format(value) },
    private val lineWidth: Dp = 1.dp,
    private val lineColor: Color = Color.Black
) {

    private val paint by lazy {
        Paint().apply {
            isAntiAlias = true
            color = lineColor
            style = PaintingStyle.Stroke
        }
    }

    private val textPaint by lazy {
        AndroidPaint().apply {
            isAntiAlias = true
            color = labelTextColor
        }
    }

    private val textBounds = AndroidRect()

    fun drawLine(drawScope: DrawScope, canvas: Canvas, drawableArea: Rect) {
        with(drawScope) {
            val lineWidth = lineWidth.toPx()
            val x = drawableArea.right - lineWidth / 2

            canvas.drawLine(
                p1 = Offset(x = x, y = drawableArea.top),
                p2 = Offset(x = x, y = drawableArea.bottom),
                paint.apply { strokeWidth = lineWidth }
            )
        }
    }

    fun drawAxisLabels(
        drawScope: DrawScope,
        canvas: Canvas,
        drawableArea: Rect,
        minValue: Float,
        maxValue: Float
    ) {
        with(drawScope) {
            val labelPaint = textPaint.apply {
                textSize = labelTextSize.toPx()
                textAlign = android.graphics.Paint.Align.RIGHT
            }
            val minLabelHeight = labelTextSize.toPx() * drawLabelOffset.toFloat()
            val totalHeight = drawableArea.height
            val labelCount = (drawableArea.height / minLabelHeight).roundToInt().coerceAtLeast(2)

            for (i in 0..labelCount) {
                val value = minValue + i * (maxValue - minValue) / labelCount
                val label = labelValueFormatter(value)
                val x = drawableArea.right - lineWidth.toPx() - labelTextSize.toPx() / 2f
                labelPaint.getTextBounds(label, 0, label.length, textBounds)
                val y =
                    drawableArea.bottom - i * (totalHeight / labelCount) + textBounds.height() / 2f
                canvas.nativeCanvas.drawText(label, x, y, labelPaint)
            }
        }
    }
}