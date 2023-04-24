package com.example.customlayout.composable

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.customlayout.R
import com.example.customlayout.ScreenViewModel
import com.example.customlayout.lazylayout.MyLazyLayout
import com.example.customlayout.lazylayout.state.rememberLazyLayoutState

@Composable
fun BubbleSortView(viewModel: ScreenViewModel) {
    val listState = rememberLazyLayoutState()
    MyLazyLayout(
        state = listState,
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .padding(dimensionResource(id = R.dimen.small_padding)),
        content = {
            items(viewModel.bubbleSortList) {

                Box(
                    modifier = Modifier
                        .padding(start = 4.dp)
                        .background(Color.Cyan)
                        .width(10.dp)
                        .height(with(LocalDensity.current) {
                            it.value.value.toDp()
                        })
                ) {

                }
            }
        }
    )

    Button(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = dimensionResource(id = R.dimen.small_padding)),
        onClick = {
            viewModel.onStartBubbleSort()
        }) {
        Text(
            text = stringResource(id = R.string.sort_button_title),
            textAlign = TextAlign.Center
        )
    }

    Button(modifier = Modifier
        .fillMaxWidth()
        .padding(vertical = dimensionResource(id = R.dimen.small_padding)),
        onClick = {
            viewModel.refreshBubbleSort()
        }) {
        Text(text = stringResource(id = R.string.refresh_bubble_sort_button_title))
    }
}