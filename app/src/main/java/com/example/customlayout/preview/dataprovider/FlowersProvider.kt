package com.example.customlayout.preview.dataprovider

import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import com.example.customlayout.R
import com.example.customlayout.preview.data.Flower

class FlowersProvider : PreviewParameterProvider<Flower> {
    override val values: Sequence<Flower> = sequenceOf(
        Flower(
            imageResId = R.drawable.tulpan,
            descriptionId = R.string.preview_view_description_tulpa,
            titleId = R.string.preview_view_title_tulpa
        ),
        Flower(
            imageResId = R.drawable.dianthus,
            descriptionId = R.string.preview_view_description_dianthus,
            titleId = R.string.preview_view_title_dianthus
        ),
    )
}