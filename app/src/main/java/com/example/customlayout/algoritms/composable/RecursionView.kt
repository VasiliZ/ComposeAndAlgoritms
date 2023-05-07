package com.example.customlayout.algoritms.composable

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import com.example.customlayout.R
import com.example.customlayout.ScreenViewModel

@Composable
fun RecursionView(viewModel: ScreenViewModel) {

    Column {
        Button(onClick = { viewModel.onStartRecursionVisualize() }) {
            Text(text = stringResource(id = R.string.start_recursion_button_text))
        }
        LazyColumn {
            items(viewModel.recursionStack) {
                RecursionItem(it)
            }
        }
    }
}