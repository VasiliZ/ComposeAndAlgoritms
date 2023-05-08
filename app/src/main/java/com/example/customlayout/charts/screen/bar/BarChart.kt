package com.example.customlayout.charts.screen.bar

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.TweenSpec
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.graphics.Paint
import androidx.compose.ui.graphics.drawscope.drawIntoCanvas
import androidx.compose.ui.unit.dp
import com.example.customlayout.charts.screen.bar.data.BarChartData
import com.example.customlayout.charts.util.LabelDrawer
import com.example.customlayout.charts.util.XAxisDrawer
import com.example.customlayout.charts.util.YAxisDrawer
import com.example.customlayout.charts.util.axisArea
import com.example.customlayout.charts.util.barDrawableArea
import com.example.customlayout.charts.util.forEachWithArea

@Composable
fun BarChart(
    data: BarChartData,
    labelDrawer: LabelDrawer = LabelDrawer(),
    xAxisDrawer: XAxisDrawer = XAxisDrawer(),
    yAxisDrawer: YAxisDrawer = YAxisDrawer(),
    barPaint: Paint = Paint().apply { isAntiAlias = true }
) {

    val transitionAnimation = remember(data.barList) { Animatable(initialValue = 0F) }

    LaunchedEffect(data.barList) {
        transitionAnimation.animateTo(1F, animationSpec = TweenSpec(durationMillis = 500))
    }

    Canvas(
        modifier = Modifier
            .height(300.dp)
            .fillMaxSize()
            .drawBehind {
                drawIntoCanvas {
                    val (xAxisArea, yAxisArea) = axisArea(
                        this,
                        size,
                        xAxisDrawer = xAxisDrawer,
                        labelDrawer = labelDrawer
                    )

                    val barDrawableArea = barDrawableArea(xAxisArea)

                    xAxisDrawer.drawLine(this, it, drawableArea = xAxisArea)
                    yAxisDrawer.drawLine(this, canvas = it, yAxisArea)

                    data.forEachWithArea(
                        this, barDrawableArea, transitionAnimation.value, labelDrawer
                    ) { rect, bar ->
                        it.drawRect(rect, barPaint.apply { color = bar.color })
                    }
                }
            },
        onDraw = {
            drawIntoCanvas {
                val (xAxisArea, yAxisArea) = axisArea(
                    this,
                    size,
                    xAxisDrawer = xAxisDrawer,
                    labelDrawer = labelDrawer
                )

                val barDrawableArea = barDrawableArea(xAxisArea)

                data.forEachWithArea(
                    this,
                    barDrawableArea = barDrawableArea,
                    labelDrawer = labelDrawer,
                    progress = transitionAnimation.value,
                ) { rect, bar ->
                    labelDrawer.drawLabel(
                        this, it, label = bar.label, barArea = rect
                    )
                }

                yAxisDrawer.drawAxisLabels(
                    this,
                    it,
                    drawableArea = yAxisArea,
                    maxValue = data.maxY,
                    minValue = data.minY
                )
            }
        })
}