package com.example.customlayout.charts.screen.pie

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.TweenSpec
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.drawscope.drawIntoCanvas
import androidx.compose.ui.unit.dp
import com.example.customlayout.ScreenViewModel
import com.example.customlayout.charts.screen.pie.util.SliceDrawer

@Composable
fun ColumnScope.PieChart(viewModel: ScreenViewModel) {

    val slices = viewModel.pieChartDataModel.pieChartData.slices

    val transition = remember(slices) {
        Animatable(0f)
    }

    LaunchedEffect(slices) {
        transition.animateTo(1F, animationSpec = TweenSpec(durationMillis = 500))
    }

    val sliceDrawer = SliceDrawer(viewModel.pieChartDataModel.sliceWidth.value)

    Canvas(modifier = Modifier

        .align(Alignment.CenterHorizontally)
        .height(200.dp)
        .fillMaxWidth(),
        onDraw = {
            drawIntoCanvas {
                var startArc = 0f
                slices.forEach { slice ->
                    val arc = calculateAngle(
                        sliceLength = slice.value,
                        totalLength = viewModel.pieChartDataModel.pieChartData.totalSlicesSum,
                        progress = transition.value
                    )

                    //get the same rect for each slice and draw it one by one
                    //slice depends on calculated arc
                    //animated state change slices arcs and redrawing it again and again
                    //start arc changes for each slice and grow until animate in progress
                    sliceDrawer.drawSlice(
                        canvas = drawContext.canvas,
                        area = size,
                        startAngle = startArc,
                        sweepAngle = arc,
                        slice = slice
                    )
                    startArc += arc
                }
            }
        })
}

private fun calculateAngle(sliceLength: Float, totalLength: Float, progress: Float): Float =
    360F * sliceLength * progress / totalLength