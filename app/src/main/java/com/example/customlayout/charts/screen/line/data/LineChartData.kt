package com.example.customlayout.charts.screen.line.data

data class LineChartData(
    val points: List<LineChartPoint>,
    val pad: Float = 20f,
) {

    val minValue = 0F
    val maxValue: Float = points.maxOf { it.value }

    val maxY = maxValue + (maxValue - minValue) * pad / 100
}