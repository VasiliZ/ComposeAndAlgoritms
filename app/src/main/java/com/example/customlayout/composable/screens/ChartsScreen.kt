package com.example.customlayout.composable.screens

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.customlayout.R
import com.example.customlayout.ScreenViewModel
import com.example.customlayout.charts.data.ChartType
import com.example.customlayout.charts.screen.bar.BarChart
import com.example.customlayout.charts.screen.bar.ChangeBarsCount
import com.example.customlayout.charts.screen.bar.LabelDrawingChooser
import com.example.customlayout.charts.screen.line.LineChart
import com.example.customlayout.charts.screen.pie.PieChart
import com.example.customlayout.charts.screen.pie.PieChartSlider
import com.example.customlayout.charts.screen.pie.data.PieChartModel

@Composable
fun ChartsScreen(viewModel: ScreenViewModel) {
    BackHandler {
        viewModel.toHomeScreen()
    }
    Column(
        modifier = Modifier
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        when (viewModel.chartScreen.value) {
            ChartType.HOME -> {
                ChartType.values().forEach {
                    if (it != ChartType.HOME) {
                        Button(
                            onClick = { viewModel.onSelectChartsScreen(it) }) {
                            Text(text = stringResource(id = it.chartTypeButtonTitleResId))
                        }
                    }
                }
            }

            ChartType.BAR -> {
                BackHandler {
                    viewModel.moveToChartsList()
                }

                Column(modifier = Modifier.padding(16.dp)) {

                    BarChart(
                        viewModel.barChartModel.barChartData,
                        labelDrawer = viewModel.barChartModel.labelDrawer
                    )
                    Spacer(modifier = Modifier.height(30.dp))
                    LabelDrawingChooser {
                        viewModel.barChartModel.onChangeBarsLocation(it)
                    }

                    ChangeBarsCount(
                        barsSize = viewModel.barChartModel.barChartData.barList.size,
                        onAddBarCallback = {
                            viewModel.barChartModel.onAddNewBar()
                        },
                        onRemoveBarCallBack = {
                            viewModel.barChartModel.onRemoveBar()
                        }
                    )
                }
            }

            ChartType.LINE -> {
                LineChart(data = viewModel.lineChartDataModel) {
                    viewModel.moveToChartsList()
                }
            }

            ChartType.PIE -> {
                BackHandler {
                    viewModel.moveToChartsList()
                }

                Column(
                    Modifier
                        .fillMaxSize()
                        .padding(16.dp),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    PieChart(viewModel)
                    PieChartSlider(viewModel = viewModel)

                    val listSlicesSize by remember {
                        derivedStateOf {
                            viewModel.pieChartDataModel.pieChartData.slices.size
                        }

                    }
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Button(
                            enabled = listSlicesSize > PieChartModel.MIN_SLICES_COUNT,
                            onClick = {
                                viewModel.onRemoveSliceButtonClick()
                            }) {
                            Text(text = stringResource(id = R.string.remove_slice))
                        }

                        Text(text = listSlicesSize.toString())

                        Button(
                            enabled = listSlicesSize < PieChartModel.MAX_SLICES_COUNT,
                            onClick = { viewModel.onAddMoreSlicesButtonClick() }) {
                            Text(text = stringResource(id = R.string.add))
                        }
                    }
                }
            }
        }
    }
}