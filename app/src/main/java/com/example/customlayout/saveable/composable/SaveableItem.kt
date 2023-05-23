package com.example.customlayout.saveable.composable

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.customlayout.saveable.data.SaveableData
import com.example.customlayout.saveable.data.simpleItemSaver

@Composable
fun SaveableItem(saveableData: SaveableData) {

    Card(modifier = Modifier.padding(8.dp).fillMaxWidth()) {

        val clicked = rememberSaveable(saver = simpleItemSaver) {
            saveableData
        }
        Checkbox(checked = clicked.isChecked.value, onCheckedChange = {
            clicked.check()
        })
        Text(text = saveableData.title)
        Spacer(modifier = Modifier.height(8.dp))
        Text(text = saveableData.description)
        Spacer(modifier = Modifier.height(8.dp))
    }
}