package com.example.customlayout.charts.util

import android.graphics.Paint
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.graphics.Canvas
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.graphics.nativeCanvas
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.sp
import com.example.customlayout.charts.util.data.DrawLocationType

class LabelDrawer(
    private val drawLocationLType: DrawLocationType = DrawLocationType.INSIDE,
    private val axisLabelFormatter: AxisLabelFormatter = { value -> "$value" },
    private val labelTextSize: TextUnit = 12.sp,
) {
    private val labelTextArea: Float? = null
    private val paint by lazy {
        Paint().apply {
            isAntiAlias = true
            color = android.graphics.Color.BLACK
            textAlign = Paint.Align.CENTER
            textSize = labelTextSize.value
        }
    }

    fun getLabelOffset(drawScope: DrawScope): Float {
        return with(drawScope) {
            when (drawLocationLType) {
                DrawLocationType.OUTSIDE -> 3f / 2f * labelTextHeight(this)
                DrawLocationType.X_AXIS -> labelTextHeight(this)
                else -> 0F
            }
        }
    }

    fun drawLabel(
        drawScope: DrawScope,
        canvas: Canvas,
        label: Any?,
        barArea: Rect,
    ) {
        with(drawScope) {
            val xCenter = barArea.left + barArea.width / 2f
            val yCenter = when (drawLocationLType) {
                DrawLocationType.INSIDE -> (barArea.top + barArea.bottom) / 2
                DrawLocationType.OUTSIDE -> barArea.top - labelTextSize.toPx() / 2
                DrawLocationType.X_AXIS -> barArea.bottom + labelTextHeight(drawScope)
            }

            val labelValue = axisLabelFormatter(label)
            canvas.nativeCanvas.drawText(labelValue, xCenter, yCenter, paint.apply {
                textSize = labelTextSize.toPx()
            })
        }
    }

    private fun labelTextHeight(drawScope: DrawScope): Float {
        return with(drawScope) { labelTextArea ?: (1.5f * labelTextSize.toPx()) }
    }
}