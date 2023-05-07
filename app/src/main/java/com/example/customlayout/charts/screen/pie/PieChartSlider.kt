package com.example.customlayout.charts.screen.pie

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Slider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.customlayout.R
import com.example.customlayout.ScreenViewModel

private const val MAX_SLIDER_VALUE = 100F
private const val INITIAL_SLIDER_VALUE = 10F

@Composable
fun PieChartSlider(viewModel: ScreenViewModel) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = stringResource(id = R.string.slice_thickness),
            modifier = Modifier
                .align(Alignment.CenterVertically)
                .padding(end = 16.dp)
        )
        Slider(
            value = viewModel.pieChartDataModel.sliceWidth.value,
            onValueChange = {
                viewModel.onUpdatePieChartViewWith(it)
            },
            valueRange = INITIAL_SLIDER_VALUE.rangeTo(MAX_SLIDER_VALUE)
        )
    }
}