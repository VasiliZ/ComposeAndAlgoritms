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

class XAxisDrawer(
    private val lineWidth: Dp = 1.dp,
    private val lineColor: Color = Color.Black,
    private val axisLabelFormatter: AxisLabelFormatter = { value -> "$value" },
    private val labelTextSize: TextUnit = 12.sp
) {

    private val paint by lazy {
        Paint().apply {
            isAntiAlias = true
            color = lineColor
            style = PaintingStyle.Stroke
        }
    }

    private val textPaint by lazy {
        android.graphics.Paint().apply {
            isAntiAlias = true
            color = android.graphics.Color.BLACK
        }
    }

    fun requireHeight(drawScope: DrawScope): Float {
        with(drawScope) {
            return lineWidth.toPx()
        }
    }

    fun drawLine(drawScope: DrawScope, canvas: Canvas, drawableArea: Rect) {
        with(drawScope) {
            val lineWidth = lineWidth.toPx()
            val y = drawableArea.top + lineWidth / 2
            canvas.drawLine(
                p1 = Offset(x = drawableArea.left, y = y),
                p2 = Offset(x = drawableArea.right, y),
                paint = paint.apply { strokeWidth = lineWidth }
            )
        }
    }

    fun drawXAxisLabels(
        drawScope: DrawScope,
        canvas: Canvas,
        drawableArea: Rect,
        labels: List<*>
    ) {
        with(drawScope) {
            val labelPaint = textPaint.apply {
                textSize = labelTextSize.toPx()
                textAlign = android.graphics.Paint.Align.CENTER
            }
            val labelIncrements = drawableArea.width / (labels.size - 1)
            labels.forEachIndexed { index, label ->
                if (index.rem(1) == 0) {
                    val labelValue = axisLabelFormatter(label)
                    val x = drawableArea.left + labelIncrements * index
                    val y = drawableArea.bottom + 1.5F * labelTextSize.toPx()
                    canvas.nativeCanvas.drawText(labelValue, x, y, labelPaint)
                }
            }
        }
    }
}