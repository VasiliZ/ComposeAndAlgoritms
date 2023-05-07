package com.example.customlayout.algoritms.composable

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.dp
import com.example.customlayout.R
import com.example.customlayout.ScreenViewModel

@Composable
fun QuickSortView(viewModel: ScreenViewModel) {
    LazyRow(verticalAlignment = Alignment.Bottom) {
        items(viewModel.quickSortList) {

            Box(
                modifier = Modifier
                    .padding(end = 4.dp)
                    .size(
                        height = with(Density(LocalContext.current)) { it.value.value.toDp() },
                        width = 10.dp
                    )
                    .background(color = if (it.isPivot.value) Color.Red else Color.Green)
            ) {

            }
        }
    }
    Button(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = dimensionResource(id = R.dimen.small_padding)),
        onClick = { viewModel.onStartQuickSort() }) {
        Text(text = stringResource(id = R.string.quick_sort_button_title))
    }

    Button(modifier = Modifier
        .fillMaxWidth()
        .padding(vertical = dimensionResource(id = R.dimen.small_padding)),
        onClick = {
            viewModel.refreshQuickSort()
        }) {
        Text(text = stringResource(id = R.string.refresh_quick_sort_button_title))
    }
}