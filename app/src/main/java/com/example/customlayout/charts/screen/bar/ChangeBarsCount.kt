package com.example.customlayout.charts.screen.bar

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.example.customlayout.R

private const val MIN_BAR_LIST_SIZE = 1
private const val MAX_BAR_LIST_SIZE = 7

@Composable
fun ChangeBarsCount(
    barsSize: Int,
    onAddBarCallback: () -> Unit,
    onRemoveBarCallBack: () -> Unit
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        Arrangement.SpaceBetween,
        Alignment.CenterVertically
    ) {
        Button(
            enabled = barsSize > MIN_BAR_LIST_SIZE,
            onClick = {
                onRemoveBarCallBack()
            }) {
            Text(text = stringResource(id = R.string.remove_slice))
        }

        Text(text = barsSize.toString())
        Button(
            enabled = barsSize < MAX_BAR_LIST_SIZE,
            onClick = {
                onAddBarCallback()
            }) {
            Text(text = stringResource(id = R.string.add))
        }
    }
}