package com.pocketsecurities.composeuidemo.preview.loading

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.pocketsecurities.composeuidemo.ui.theme.ComposeUIDemoTheme
import com.pocketsecurities.pocketcomposecomponent.color_333333
import com.pocketsecurities.pocketcomposecomponent.component.loading.HorizontalProgressComponent

@Preview
@Composable
private fun HorizontalProgressComponentPreview() {
    ComposeUIDemoTheme {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp)
                .background(color_333333),
            contentAlignment = Alignment.Center
        ) {
            HorizontalProgressComponent(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(20.dp)
                    .padding(horizontal = 10.dp),
                percentage = 0.87f
            )
        }
    }

}