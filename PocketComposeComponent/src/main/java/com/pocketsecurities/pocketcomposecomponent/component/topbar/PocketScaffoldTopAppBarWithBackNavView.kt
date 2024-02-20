package com.pocketsecurities.pocketcomposecomponent.component.topbar

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.pocketsecurities.pocketcomposecomponent.R
import com.pocketsecurities.pocketcomposecomponent.color_252525
import com.pocketsecurities.pocketcomposecomponent.component.button.PocketIconButton
import com.pocketsecurities.pocketcomposecomponent.component.text.PocketText
import com.pocketsecurities.pocketcomposecomponent.component.text.PocketTextConfig
import com.pocketsecurities.pocketcomposecomponent.component.text.TextWithIcon
import com.pocketsecurities.pocketcomposecomponent.component.text.TextWithIconViewState

data class PocketTopBarConfig(
    val needInformation: Boolean = false,
    val textConfig: PocketTextConfig = PocketTextConfig()
) {
    companion object {
        val DEFAULT_TEXT_STYLE = PocketTopBarConfig()
    }
}

/**
 * @sample PocketScaffoldTopBarComponent
 *
 * @param config app bar 設定(預設為文字, 資訊icon)
 * @param onInfoClick export 資訊icon點擊事件
 * @param onNavigationClick export navigation點擊事件
 * @param onActionClick export action點擊事件
 * @param titleContent title內容(中間)
 * @param navContent nav內容(左側)
 * @param actionContent action內容(右側)
 * @param background 背景顏色
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PocketScaffoldTopBarComponent(
    modifier: Modifier = Modifier,
    config: PocketTopBarConfig = PocketTopBarConfig(),
    onInfoClick: (() -> Unit)? = null,
    onNavigationClick: (() -> Unit)? = null,
    onActionClick: (() -> Unit)? = null,
    titleContent: @Composable () -> Unit = {
        Row(
            modifier = Modifier.fillMaxHeight(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            PocketText(
                config = config.textConfig
            )
            Spacer(modifier = Modifier.width(5.dp))
            if (config.needInformation) {
                PocketIconButton(
                    drawableRes = R.drawable.preview_ic_information,
                    iconSize = 22.dp,
                    onIconClick = {
                        onInfoClick?.invoke()
                    }
                )
            }
        }
    },
    navContent: @Composable () -> Unit = {},
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


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PocketScaffoldTopAppBarWithBackNavView(
    modifier: Modifier = Modifier,
    appBarBackground: Color = MaterialTheme.colorScheme.background,
    titleText: String = "",
    titleStyle: TextStyle = LocalTextStyle.current,
    needInformation: Boolean = false,
    onNavigationClick: () -> Unit = {},
    navContent: @Composable () -> Unit = {
//        IconButton(
//            onClick = { onNavigationClick() }
//        ) {
//            Image(
//                painter = painterResource(id = R.drawable.ic_pocket_back),
//                contentDescription = null,
//                modifier = Modifier.size(24.dp),
//            )
//        }
    },
    actionContent: @Composable RowScope.() -> Unit = {}
) {
    CenterAlignedTopAppBar(
        modifier = modifier,
        title = {
            Box(
                modifier = Modifier.fillMaxHeight(),
                contentAlignment = Alignment.Center
            ) {
                if (needInformation) {
                    TextWithIcon(
                        endViewState = TextWithIconViewState(
                            drawableRes = R.drawable.preview_ic_information,
                            drawableSize = 22.dp,
                            onClick = {

                            }
                        )
                    ) {
                        Text(
                            text = titleText,
                            style = titleStyle
                        )
                    }
                } else {
                    Text(
                        text = titleText,
                        style = titleStyle
                    )

                }
            }
        },
        navigationIcon = navContent,
        actions = actionContent,
        colors = TopAppBarDefaults.centerAlignedTopAppBarColors(containerColor = appBarBackground)
    )
}

@Composable
@Preview
private fun PreviewPocketScaffoldTopAppBarWithBackNavView() {
    PocketScaffoldTopAppBarWithBackNavView(
        modifier = Modifier.height(50.dp),
        titleText = "今日說法"
    )
}

@Composable
@Preview
private fun PreviewPocketScaffoldTopAppBarWithBackNavView2() {
    PocketScaffoldTopBarComponent(
        modifier = Modifier.height(50.dp),
        config = PocketTopBarConfig(
            needInformation = true,
            textConfig = PocketTextConfig(
                value = "今日說法",
                style = TextStyle(
                    fontSize = 17.sp,
                    fontWeight = FontWeight(500)
                )
            )
        ),
        background = color_252525
    )
}



