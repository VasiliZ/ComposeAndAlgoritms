package com.example.customlayout.charts.screen.bar.data

private const val PAD = 10f

data class BarChartData(
    val barList: List<Bar>,
) {

    val maxYValue = barList.maxOf { it.value }
    private val minYValue = barList.minOf { it.value }

    val maxY = maxYValue + (maxYValue - minYValue) * PAD / 100
    val minY = 0F
}