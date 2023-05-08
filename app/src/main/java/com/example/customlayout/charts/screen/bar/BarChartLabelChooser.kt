package com.example.customlayout.charts.screen.bar

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.example.customlayout.charts.util.data.DrawLocationType

@Composable
fun LabelDrawingChooser(
    onChooseLabelLocation: (DrawLocationType) -> Unit
) {

    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        DrawLocationType.values().forEach {
            Button(onClick = {
                onChooseLabelLocation(it)
            }) {
                Text(text = it.name)
            }
        }
    }
}