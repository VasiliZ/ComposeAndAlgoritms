package com.example.customlayout.animation.screen

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.ScrollableTabRow
import androidx.compose.material3.Tab
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.customlayout.ScreenViewModel
import com.example.customlayout.animation.model.AnimationType
import kotlinx.coroutines.launch

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun AnimationScreen(model: ScreenViewModel) {

    val coroutineScope = rememberCoroutineScope()
    val pagerState = rememberPagerState()
    Column {
        BackHandler {
            model.toHomeScreen()
        }

        ScrollableTabRow(
            edgePadding = 0.dp,
            selectedTabIndex = pagerState.currentPage
        ) {
            AnimationType.values().forEachIndexed { index, animationType ->
                Tab(
                    selected = index == pagerState.currentPage,
                    onClick = {
                        coroutineScope.launch {
                            pagerState.animateScrollToPage(index)
                        }
                    },
                    text = {
                        Text(text = stringResource(id = animationType.resTitleId))
                    }
                )
            }
        }

        HorizontalPager(
            pageCount = AnimationType.values().size,
            state = pagerState
        ) {
            Box(
                Modifier
                    .fillMaxWidth()
                    .fillMaxSize()
                    .padding(top = 72.dp),
                contentAlignment = Alignment.Center,
            ) {
                AnimationType.values()[pagerState.currentPage].View()
            }
        }
    }
}