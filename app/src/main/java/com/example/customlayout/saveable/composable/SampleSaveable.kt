package com.example.customlayout.saveable.composable

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import com.example.customlayout.saveable.data.provider.SaveableDataProvider

@Composable
fun SampleSaveable() {
    LazyColumn(content = {
        items(SaveableDataProvider.provideSaveableData()) {
            SaveableItem(it)
        }
    }
    )
}