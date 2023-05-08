package com.example.customlayout.charts.screen.line

import androidx.activity.compose.BackHandler
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.TweenSpec
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.drawscope.drawIntoCanvas
import androidx.compose.ui.unit.dp
import com.example.customlayout.charts.screen.line.data.LineChartDataModel
import com.example.customlayout.charts.screen.line.util.LineDrawer
import com.example.customlayout.charts.screen.line.util.computeDrawableArea
import com.example.customlayout.charts.screen.line.util.computeLinePath
import com.example.customlayout.charts.screen.line.util.computeXAxisDrawableArea
import com.example.customlayout.charts.screen.line.util.computeXAxisLabelsDrawableArea
import com.example.customlayout.charts.screen.line.util.computeYAxisDrawableArea
import com.example.customlayout.charts.util.XAxisDrawer
import com.example.customlayout.charts.util.YAxisDrawer

@Composable
fun LineChart(
    data: LineChartDataModel,
    xAxisDrawer: XAxisDrawer = XAxisDrawer(),
    yAxisDrawer: YAxisDrawer = YAxisDrawer(),
    horizontalOffset: Float = 5f,
    lineDrawer: LineDrawer = LineDrawer(),
    onBackPressCallBack: () -> Unit
) {
    val transitionAnimation = remember(data.lineChartData) { Animatable(initialValue = 0F) }

    LaunchedEffect(data.lineChartData) {
        transitionAnimation.snapTo(0F)
        transitionAnimation.animateTo(1F, animationSpec = TweenSpec(durationMillis = 500))
    }

    BackHandler {
        onBackPressCallBack()
    }

    Canvas(
        modifier = Modifier
            .padding(16.dp)
            .height(250.dp)
            .fillMaxSize(),
    ) {
        drawIntoCanvas {
            val yAxisDrawableArea =
                computeYAxisDrawableArea(
                    xAxisDrawer.requireHeight(this),
                    size = size
                )

            val xAxisDrawableArea = computeXAxisDrawableArea(
                yAxisDrawableArea.width,
                xAxisDrawer.requireHeight(this),
                size = size
            )

            val xAxisLabelDrawableArea =
                computeXAxisLabelsDrawableArea(xAxisDrawableArea, horizontalOffset)

            val chartDrawableArea =
                computeDrawableArea(xAxisDrawableArea, yAxisDrawableArea, size, horizontalOffset)

            lineDrawer.drawLine(
                this, it, linePath = computeLinePath(
                    chartDrawableArea,
                    data.lineChartData,
                    transitionAnimation.value
                )
            )

            xAxisDrawer.drawLine(
                drawScope = this,
                drawableArea = xAxisDrawableArea,
                canvas = it
            )
            xAxisDrawer.drawXAxisLabels(
                drawScope = this,
                canvas = it,
                drawableArea = xAxisLabelDrawableArea,
                labels = data.lineChartData.points.map { it.label })

            yAxisDrawer.drawLine(
                drawScope = this,
                drawableArea = yAxisDrawableArea,
                canvas = it
            )

            yAxisDrawer.drawAxisLabels(
                drawScope = this,
                canvas = it,
                drawableArea = yAxisDrawableArea,
                minValue = data.lineChartData.minValue,
                maxValue = data.lineChartData.maxValue
            )
        }
    }
}