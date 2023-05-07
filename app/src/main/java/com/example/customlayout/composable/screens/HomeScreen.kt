package com.example.customlayout.composable.screens

import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.customlayout.Screen
import com.example.customlayout.ScreenViewModel

@Composable
fun BoxScope.HomeScreen(viewModel: ScreenViewModel) {

    Column(
        modifier = Modifier
            .wrapContentHeight()
            .fillMaxWidth()
            .align(Alignment.Center)
    ) {
        Screen.values().forEach {
            if (it != Screen.HOME) {
                Button(
                    modifier = Modifier
                        .padding(horizontal = 16.dp, vertical = 8.dp)
                        .fillMaxWidth(),
                    onClick = { viewModel.onHomeScreenButtonClick(it) }) {
                    Text(text = stringResource(id = it.screenNameResId))
                }
            }
        }
    }
}