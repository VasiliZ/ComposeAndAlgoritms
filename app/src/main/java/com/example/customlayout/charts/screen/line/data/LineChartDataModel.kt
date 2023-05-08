package com.example.customlayout.charts.screen.line.data

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import kotlin.random.Random

private const val MAX_LINE_CHART_VALUE = 150
private const val MIN_LINE_CHART_VALUE = 10

class LineChartDataModel {

    var lineChartData by mutableStateOf(
        LineChartData(
            points = listOf(
                LineChartPoint(randomValue(), "Label1"),
                LineChartPoint(randomValue(), "Label2"),
                LineChartPoint(randomValue(), "Label3"),
                LineChartPoint(randomValue(), "Label4"),
                LineChartPoint(randomValue(), "Label5"),
            )
        )
    )

    private fun randomValue(): Float {
        return Random.nextInt(MIN_LINE_CHART_VALUE, MAX_LINE_CHART_VALUE).toFloat()
    }
}