package com.pocketsecurities.composeuidemo.preview.pager

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.pocketsecurities.pocketcomposecomponent.component.pager.PagerDotComponent

@Preview
@Composable
private fun PagerDotComponentPreview() {
    PagerDotComponent(
        currentPage = 1,
        pageSize = 3
    )
}