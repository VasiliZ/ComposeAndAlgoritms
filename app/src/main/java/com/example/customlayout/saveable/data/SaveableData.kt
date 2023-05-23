package com.example.customlayout.saveable.data

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.Stable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.mapSaver

@Stable
data class SaveableData(
    val title: String,
    val description: String,
    var isChecked: MutableState<Boolean> = mutableStateOf(false)
){

    fun check(){
        isChecked.value = !isChecked.value
    }
}

@Suppress("UNCHECKED_CAST")
val simpleItemSaver = run {
    val title = "title"
    val description = "description"
    val isChecked = "isChecked"
    mapSaver(
        save ={
            mapOf(title to it.title, description to it.description, isChecked to it.isChecked)
        } ,
        restore = {
            SaveableData(it[title] as String, it[description] as String, it[isChecked] as MutableState<Boolean>)
        }
    )
}

