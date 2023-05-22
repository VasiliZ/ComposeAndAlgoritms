package com.example.customlayout.animation.composable

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.customlayout.R
import com.example.customlayout.TestTags.START_ANIMATION_BUTTON_TAG
import com.example.customlayout.TestTags.START_ANIMATION_BUTTON_TEXT_TAG

@Composable
fun StartAnimationButton(
    startAnimationCallback: () -> Unit
) {

    Button(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp)
            .testTag(START_ANIMATION_BUTTON_TAG),
        onClick = { startAnimationCallback() }) {
        Text(
            text = stringResource(id = R.string.start_animation_button_text),
            modifier = Modifier.testTag(START_ANIMATION_BUTTON_TEXT_TAG)
        )
    }
}