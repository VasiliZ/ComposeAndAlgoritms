package com.example.customlayout.algoritms.composable

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import com.example.customlayout.R
import com.example.customlayout.ScreenViewModel

@Composable
fun NThreeView(viewModel: ScreenViewModel) {
    Column {
        NNodeThree(viewModel.nNodeThree.value)
        Button(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = dimensionResource(id = R.dimen.small_padding)),
            onClick = { viewModel.onTraverseDepthFirst() }) {
            Text(text = stringResource(id = R.string.n_three_traverse_depth_button_title))
        }

        Button(modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = dimensionResource(id = R.dimen.small_padding)),
            onClick = {
                viewModel.refreshNThreeView()
            }) {
            Text(text = stringResource(id = R.string.refresh_traverse_depth_view_button_title))
        }
    }
}