package com.pocketsecurities.composeuidemo.preview.image

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.pocketsecurities.pocketcomposecomponent.R
import com.pocketsecurities.pocketcomposecomponent.component.image.BoxAsyncImage
import com.pocketsecurities.pocketcomposecomponent.component.image.BoxAsyncImageConfig


@Preview
@Composable
private fun BoxAsyncImagePreview() {
    BoxAsyncImage(
        modifier = Modifier,
        viewConfig = BoxAsyncImageConfig(
            errorDrawable = R.drawable.preview_ic_information
        )
    )
}