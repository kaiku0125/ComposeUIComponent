package com.pocketsecurities.composeuidemo.preview.topbar

import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.pocketsecurities.composeuidemo.ui.theme.ComposeUIDemoTheme
import com.pocketsecurities.composeuidemo.ui.theme.DarkComposeUIDemoTheme
import com.pocketsecurities.pocketcomposecomponent.component.text.PocketTextConfig
import com.pocketsecurities.pocketcomposecomponent.component.topbar.BaseTopBarComponent
import com.pocketsecurities.pocketcomposecomponent.component.topbar.PocketAppBarWithBackNavigation
import com.pocketsecurities.pocketcomposecomponent.component.topbar.PocketTopBarConfig

@Preview
@Composable
private fun BaseTopBarComponentPreview() {
    DarkComposeUIDemoTheme {
        BaseTopBarComponent(
            modifier = Modifier.height(100.dp),
            config = PocketTopBarConfig(
                needInformation = true,
                textConfig = PocketTextConfig(
                    value = "標題",
                    style = TextStyle(
                        fontSize = 17.sp,
                        fontWeight = FontWeight(500)
                    )
                )
            )
        )
    }
}

@PreviewLightDark
@Composable
private fun AppBarWithBackNavigationPreview() {
    ComposeUIDemoTheme {
        PocketAppBarWithBackNavigation(
            title = "標題",
            onInfoClick = {},
            onBackClick = {}
        )
    }
}