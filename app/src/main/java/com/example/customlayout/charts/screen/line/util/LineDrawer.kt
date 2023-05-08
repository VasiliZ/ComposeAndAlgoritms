package com.example.customlayout.charts.screen.line.util

import androidx.compose.ui.graphics.Canvas
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Paint
import androidx.compose.ui.graphics.PaintingStyle
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

class LineDrawer(private val lineWidth: Dp = 3.dp, val color: Color = Color.Red) {

    private val paint by lazy {
        Paint().apply {
            isAntiAlias = true
            color = this@LineDrawer.color
            style = PaintingStyle.Stroke
        }
    }

    fun drawLine(drawScope: DrawScope, canvas: Canvas, linePath: Path) {
        canvas.drawPath(path = linePath, paint = paint.apply {
            strokeWidth = with(drawScope) {
                lineWidth.toPx()
            }
        })
    }
}