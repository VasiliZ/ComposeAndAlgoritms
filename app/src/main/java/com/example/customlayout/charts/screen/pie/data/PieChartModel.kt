package com.example.customlayout.charts.screen.pie.data

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.Color
import kotlin.random.Random

private const val MIN_SLICE_VALUE = 10
private const val MAX_SLICE_VALUE = 30
private const val INITIAL_SLIDER_VALUE = 25f

class PieChartModel {

    companion object {
        const val MAX_SLICES_COUNT = 13
        const val MIN_SLICES_COUNT = 2
    }

    private val colors = mutableListOf(
        Color(0XFFF44336),
        Color(0XFFE91E63),
        Color(0XFF9C27B0),
        Color(0XFF673AB7),
        Color(0XFF3F51B5),
        Color(0XFF03A9F4),
        Color(0XFF009688),
        Color(0XFFCDDC39),
        Color(0XFFFFC107),
        Color(0XFFFF5722),
        Color(0XFF795548),
        Color(0XFF9E9E9E),
        Color(0XFF607D8B)
    )

    val sliceWidth = mutableStateOf(INITIAL_SLIDER_VALUE)
    var pieChartData by mutableStateOf(
        PieChartData(
            slices = listOf(
                PieChartSlice(randomLength(), randomColor()),
                PieChartSlice(randomLength(), randomColor()),
                PieChartSlice(randomLength(), randomColor())
            )
        )
    )

    fun addNewSlice() {
        pieChartData = pieChartData.copy(
            slices = pieChartData.slices.toMutableList().apply {
                add(PieChartSlice(randomLength(), randomColor()))
            }.toList()
        )
    }

    private fun randomLength(): Float = Random.nextInt(MIN_SLICE_VALUE, MAX_SLICE_VALUE).toFloat()

    private fun randomColor(): Color {
        val randomIndex = Random.nextInt(colors.size)
        return colors.removeAt(randomIndex)
    }

    fun removeSlice() {
        pieChartData = pieChartData.copy(
            slices = pieChartData.slices.toMutableList().apply {
                val lastSlice = pieChartData.slices.last()
                colors.add(lastSlice.color)
                remove(lastSlice)
            }.toList()
        )
    }
}
