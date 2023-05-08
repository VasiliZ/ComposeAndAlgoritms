package com.example.customlayout.charts.util.data

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.Color
import com.example.customlayout.charts.screen.bar.data.Bar
import com.example.customlayout.charts.screen.bar.data.BarChartData
import com.example.customlayout.charts.util.LabelDrawer
import kotlin.random.Random

private const val MAX_BAR_VALUE = 120
private const val MIN_BAR_VALUE = 25

class BarChartDataModel {
    private var colors = mutableListOf(
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

    var labelDrawer by mutableStateOf(LabelDrawer())
    var labelLocation: DrawLocationType = DrawLocationType.INSIDE
        set(value) {
            labelDrawer = LabelDrawer(drawLocationLType = value)
            field = value
        }

    var barChartData by mutableStateOf(
        BarChartData(
            listOf(
                Bar(value = randomBarValue(), color = getColor(), label = "Bar 1"),
                Bar(value = randomBarValue(), color = getColor(), label = "Bar 2"),
                Bar(value = randomBarValue(), color = getColor(), label = "Bar 3"),
                Bar(value = randomBarValue(), color = getColor(), label = "Bar 4"),
            )
        )
    )

    private fun randomBarValue(): Float {
        return Random.nextInt(from = MIN_BAR_VALUE, until = MAX_BAR_VALUE).toFloat()
    }

    private fun getColor(): Color {
        val index = Random.nextInt(0, colors.size)
        return colors.removeAt(index)
    }

    fun onChangeBarsLocation(drawLocationType: DrawLocationType) {
        labelLocation = drawLocationType
    }

    fun onRemoveBar() {
        barChartData = barChartData.copy(
            barList = barChartData.barList.toMutableList().apply {
                val bar = barChartData.barList.last()
                colors.add(bar.color)
                remove(bar)
            }
        )
    }

    fun onAddNewBar() {
        barChartData = barChartData.copy(
            barList = barChartData.barList.toMutableList().apply {
                add(
                    Bar(
                        value = randomBarValue(),
                        color = getColor(),
                        label = "Bar ${barChartData.barList.size + 1}"
                    )
                )
            }
        )
    }
}