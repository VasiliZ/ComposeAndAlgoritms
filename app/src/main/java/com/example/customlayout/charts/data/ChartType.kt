package com.example.customlayout.charts.data

import com.example.customlayout.R

enum class ChartType(val chartTypeButtonTitleResId: Int) {
    PIE(R.string.pie_charts_button_title),
    LINE(R.string.line_charts_button_title),
    BAR(R.string.bar_charts_button_title),
    HOME(R.string.home_charts_button_title)
}