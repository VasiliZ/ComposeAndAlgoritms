package com.example.customlayout.lazylayout

import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import com.example.customlayout.lazylayout.contentprovider.LazyListItemProvider
import com.example.customlayout.lazylayout.scope.CustomLazyListScopeImpl
import com.example.customlayout.lazylayout.scope.CustomLazyScope

@Composable
fun rememberItemProvider(customLazyListScope: CustomLazyScope.() -> Unit): LazyListItemProvider {

    val customLazyListState = remember {
        mutableStateOf(customLazyListScope)
    }.apply {
        value = customLazyListScope
    }

    return remember {
        LazyListItemProvider(
            derivedStateOf {
                val lazyListScope = CustomLazyListScopeImpl().apply(customLazyListState.value)
                lazyListScope.items
            })
    }
}