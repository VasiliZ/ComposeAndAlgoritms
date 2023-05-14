package com.example.customlayout

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.ui.Modifier
import com.example.customlayout.animation.screen.AnimationScreen
import com.example.customlayout.composable.screens.AlgorithmsScreen
import com.example.customlayout.composable.screens.ChartsScreen
import com.example.customlayout.composable.screens.HomeScreen
import com.example.customlayout.ui.theme.CustomLayoutTheme

class MainActivity : ComponentActivity() {
    private val viewModel by viewModels<ScreenViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            CustomLayoutTheme {
                Box(modifier = Modifier.fillMaxSize()) {
                    when (viewModel.currentScreen.value) {
                        Screen.ALGORITHMS -> AlgorithmsScreen(viewModel = viewModel)
                        Screen.CHARTS -> ChartsScreen(viewModel)
                        Screen.ANIMATION -> AnimationScreen(viewModel)
                        else -> HomeScreen(viewModel)
                    }
                }
            }
        }
    }
}


