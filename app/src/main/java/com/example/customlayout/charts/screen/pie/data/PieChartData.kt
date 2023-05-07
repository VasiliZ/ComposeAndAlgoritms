package com.example.customlayout.charts.screen.pie.data

data class PieChartData(
    val slices: List<PieChartSlice>
) {
    val totalSlicesSum = slices.map { it.value }.sum()

}
