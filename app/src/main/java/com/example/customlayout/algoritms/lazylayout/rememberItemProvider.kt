package com.example.customlayout.algoritms.lazylayout

import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import com.example.customlayout.algoritms.lazylayout.contentprovider.LazyListItemProvider
import com.example.customlayout.algoritms.lazylayout.scope.CustomLazyListScopeImpl
import com.example.customlayout.algoritms.lazylayout.scope.CustomLazyScope

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