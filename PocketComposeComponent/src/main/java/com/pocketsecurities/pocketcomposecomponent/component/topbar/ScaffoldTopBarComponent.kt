package com.pocketsecurities.pocketcomposecomponent.component.topbar

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LocalContentColor
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalHapticFeedback
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.pocketsecurities.pocketcomposecomponent.R
import com.pocketsecurities.pocketcomposecomponent.component.button.PocketIconButton
import com.pocketsecurities.pocketcomposecomponent.component.text.PocketText
import com.pocketsecurities.pocketcomposecomponent.component.text.PocketTextConfig
import com.pocketsecurities.pocketcomposecomponent.extension.withEffect

data class PocketTopBarConfig(
    val needInformation: Boolean = false,
    val textConfig: PocketTextConfig = PocketTextConfig()
)


/**
 * @sample ScaffoldTopBarComponent
 *
 * @param config app bar 設定(預設為文字, 資訊icon)
 * @param onInfoClick export 資訊icon點擊事件
 * @param onNavigationClick export navigation點擊事件
 * @param titleContent title內容(中間)
 * @param navContent nav內容(左側)
 * @param actionContent action內容(右側)
 * @param background 背景顏色
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ScaffoldTopBarComponent(
    modifier: Modifier = Modifier,
    config: PocketTopBarConfig = PocketTopBarConfig(),
    onInfoClick: (() -> Unit)? = null,
    onNavigationClick: (() -> Unit)? = null,
    titleContent: @Composable () -> Unit = {
        Row(
            modifier = Modifier.fillMaxHeight(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            PocketText(
                config = config.textConfig.copy(
                    textColor = LocalContentColor.current
                )
            )
            Spacer(modifier = Modifier.width(5.dp))
            onInfoClick?.let {
                PocketIconButton(
                    drawableRes = R.drawable.preview_ic_information,
                    tint = LocalContentColor.current,
                    iconSize = 22.dp,
                    onIconClick = {
                        onInfoClick.invoke()
                    }
                )
            }
        }
    },
    navContent: @Composable () -> Unit = {
        val context = LocalContext.current
        val haptic = LocalHapticFeedback.current

        IconButton(
            modifier = Modifier.fillMaxHeight(),
            onClick = {
                onNavigationClick?.withEffect(
                    context = context,
                    haptic = haptic,
                    needSound = true,
                    needHaptic = false
                )?.invoke()
            },
            content = {
                Icon(
                    modifier = Modifier.size(24.dp),
                    painter = painterResource(id = R.drawable.ic_back),
                    contentDescription = null,
                    tint = MaterialTheme.colorScheme.onBackground,
                )
            }
        )
    },
    actionContent: @Composable RowScope.() -> Unit = {},
    background: Color = MaterialTheme.colorScheme.background
) {
    CenterAlignedTopAppBar(
        modifier = modifier,
        title = titleContent,
        navigationIcon = navContent,
        actions = actionContent,
        colors = TopAppBarDefaults.centerAlignedTopAppBarColors(containerColor = background)
    )
}

/**
 * @sample PocketAppBarWithBackNavigation 口袋 top bar 基本元件
 *
 * @param title 標題
 * @param onInfoClick export icon 點擊事件
 * @param onBackClick export 返回點擊事件
 * @param background 背景顏色
 */
@Composable
fun PocketAppBarWithBackNavigation(
    modifier: Modifier = Modifier,
    title: String = "",
    onInfoClick: (() -> Unit)? = null,
    onBackClick: () -> Unit = {},
    background: Color = MaterialTheme.colorScheme.background
) {
    ScaffoldTopBarComponent(
        modifier = modifier,
        config = PocketTopBarConfig(
            needInformation = onInfoClick != null,
            textConfig = PocketTextConfig(
                value = title,
                style = TextStyle(
                    fontSize = 17.sp,
                    fontWeight = FontWeight(500)
                )
            )
        ),
        background = background,
        onInfoClick = onInfoClick,
        onNavigationClick = onBackClick
    )
}

// ------------------------------ Preview ------------------------------
@Preview
@Composable
private fun ScaffoldTopBarComponentPreview() {
    ScaffoldTopBarComponent(
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

@Preview
@Composable
private fun PocketAppBarWithBackNavigationPreview() {
    PocketAppBarWithBackNavigation(
        title = "標題",
        onInfoClick = {},
        onBackClick = {}
    )
}



