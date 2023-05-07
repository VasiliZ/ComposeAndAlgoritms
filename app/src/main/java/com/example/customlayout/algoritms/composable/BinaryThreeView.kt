package com.example.customlayout.algoritms.composable

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import com.example.customlayout.R
import com.example.customlayout.ScreenViewModel

@Composable
fun BinaryThreeView(viewModel: ScreenViewModel) {

    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        ThreeNode(value = viewModel.three.value)
        Button(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = dimensionResource(id = R.dimen.small_padding)),
            onClick = { viewModel.onStartTraversePreorder() }) {
            Text(text = stringResource(id = R.string.binary_three_pre_order_button_title))
        }
        Button(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = dimensionResource(id = R.dimen.small_padding)),
            onClick = { viewModel.onStartTraverseInOrder() }) {
            Text(text = stringResource(id = R.string.binary_three_in_order_button_title))
        }
        Button(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = dimensionResource(id = R.dimen.small_padding)),
            onClick = { viewModel.onStartTraversePostOrder() }) {
            Text(text = stringResource(id = R.string.binary_three_post_order_button_title))
        }

        Button(modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = dimensionResource(id = R.dimen.small_padding)),
            onClick = {
                viewModel.refreshBinaryThree()
            }) {
            Text(text = stringResource(id = R.string.binary_three_refresh_button_title))
        }
    }
}