package com.example.customlayout.charts.util

import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.graphics.Canvas
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Paint
import androidx.compose.ui.graphics.PaintingStyle
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

class XAxisDrawer(
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
}