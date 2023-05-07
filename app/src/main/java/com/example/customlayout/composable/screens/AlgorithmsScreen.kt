package com.example.customlayout.composable.screens

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.customlayout.ScreenViewModel
import com.example.customlayout.algoritms.composable.BinaryThreeView
import com.example.customlayout.algoritms.composable.BubbleSortView
import com.example.customlayout.algoritms.composable.NThreeView
import com.example.customlayout.algoritms.composable.QuickSortView
import com.example.customlayout.algoritms.composable.RecursionView
import com.example.customlayout.algoritms.data.RadioGroupItemType

@Composable
fun AlgorithmsScreen(viewModel: ScreenViewModel) {

    BackHandler {
        viewModel.toHomeScreen()
    }

    Column(
        modifier = Modifier
            .padding(horizontal = 16.dp)
            .wrapContentHeight()
    ) {
        Column {
            viewModel.radioGroupList.forEach {

                Row(verticalAlignment = Alignment.CenterVertically) {
                    RadioButton(
                        selected = it == viewModel.selectedRadioButtonItem.value,
                        onClick = {
                            viewModel.selectedRadioButtonItem.value = it
                        })

                    Text(
                        modifier = Modifier.padding(start = 8.dp),
                        text = stringResource(id = it.title)
                    )
                }
            }

            when (viewModel.selectedRadioButtonItem.value) {
                RadioGroupItemType.BUBBLE_SORT -> BubbleSortView(viewModel = viewModel)
                RadioGroupItemType.QUICK_SORT -> QuickSortView(viewModel = viewModel)
                RadioGroupItemType.BINARY_THREE -> {
                    Box(modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.Center) {
                        BinaryThreeView(viewModel = viewModel)
                    }
                }

                RadioGroupItemType.N_NODES_THREE -> {
                    Box(modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.Center) {
                        NThreeView(viewModel = viewModel)
                    }
                }

                RadioGroupItemType.RECURSION -> {
                    RecursionView(viewModel)
                }
            }
        }
    }
}