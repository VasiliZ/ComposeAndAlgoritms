package com.example.customlayout

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Text
import androidx.compose.ui.Alignment.Companion.Center
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.customlayout.composable.BinaryThreeView
import com.example.customlayout.composable.BubbleSortView
import com.example.customlayout.composable.NThreeView
import com.example.customlayout.composable.QuickSortView
import com.example.customlayout.composable.RecursionView
import com.example.customlayout.data.RadioGroupItemType
import com.example.customlayout.ui.theme.CustomLayoutTheme

class MainActivity : ComponentActivity() {
    private val viewModel by viewModels<ScreenViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            CustomLayoutTheme {
                Column(
                    modifier = Modifier
                        .padding(horizontal = 16.dp)
                        .wrapContentHeight()
                ) {
                    Column {
                        viewModel.radioGroupList.forEach {

                            Row(verticalAlignment = CenterVertically) {
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
                                Box(modifier = Modifier.fillMaxWidth(), contentAlignment = Center) {
                                    BinaryThreeView(viewModel = viewModel)
                                }
                            }

                            RadioGroupItemType.N_NODES_THREE -> {
                                Box(modifier = Modifier.fillMaxWidth(), contentAlignment = Center) {
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
        }
    }
}


