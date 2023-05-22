package com.example.customlayout.preview

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import com.example.customlayout.preview.data.Flower
import com.example.customlayout.preview.dataprovider.FlowersProvider

@Preview(showBackground = true)
@Composable
fun PreviewWithBackground() {
    PreviewView()
}

@Preview(
    showBackground = true,
    device = "spec:width=411dp,height=891dp,dpi=420,isRound=false,chinSize=0dp,orientation=portrait"
)
@Composable
fun PreviewWithBackgroundWithSpec() {
    PreviewView()
}

@CustomFontPreviews
@Composable
fun FontPreviews() {
    PreviewView()
}

@CustomLocalePreviews
@Composable
fun LocalePreviews() {
    PreviewView()
}

@Preview(showBackground = true)
@Composable
fun PreviewWithLargeDataSet(
    @PreviewParameter(FlowersProvider::class) flower: Flower
) {
   PreviewView(flower)
}