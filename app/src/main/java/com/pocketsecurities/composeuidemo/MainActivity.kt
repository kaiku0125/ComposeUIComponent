package com.pocketsecurities.composeuidemo

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.pocketsecurities.composeuidemo.preview.spinner.SpinnerPreviewType
import com.pocketsecurities.composeuidemo.ui.theme.DarkComposeUIDemoTheme
import com.pocketsecurities.pocketcomposecomponent.component.image.BoxAsyncImage
import com.pocketsecurities.pocketcomposecomponent.component.image.BoxAsyncImageConfig
import com.pocketsecurities.pocketcomposecomponent.component.text.PocketText
import com.pocketsecurities.pocketcomposecomponent.component.text.PocketTextConfig
import com.pocketsecurities.pocketcomposecomponent.R
import com.pocketsecurities.pocketcomposecomponent.color_333333
import com.pocketsecurities.pocketcomposecomponent.component.spinner.PocketSpinnerComponent
import com.pocketsecurities.pocketcomposecomponent.component.spinner.SpinnerType
import com.pocketsecurities.pocketcomposecomponent.component.topbar.PocketAppBarWithBackNavigation

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            DarkComposeUIDemoTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = Color.Black
                ) {
                    MainActivityContent()
                }
            }
        }
    }
}

@Composable
private fun MainActivityContent() {
    Scaffold(
        topBar = {
            PocketAppBarWithBackNavigation(
                modifier = Modifier.height(100.dp),
                title = "標題",
                onInfoClick = {},
                onBackClick = {},
                background = color_333333
            )
        }
    ) { paddingValues ->
        Column(modifier = Modifier.padding(paddingValues)) {
            PocketText(
                config = PocketTextConfig(
                    value = "Hello, World!"
                )
            )
            BoxAsyncImage(
                modifier = Modifier.size(50.dp),
                viewConfig = BoxAsyncImageConfig(
                    url = "myUrl",
                    errorDrawable = R.drawable.preview_ic_information
                )
            )

            var currentType by remember {
                mutableStateOf<SpinnerType>(SpinnerPreviewType.SPOT)
            }

            PocketSpinnerComponent(
                modifier = Modifier,
                list = SpinnerPreviewType.getAll(),
                itemHeight = 30.dp,
                chosenTextConfig = PocketTextConfig(
                    value = stringResource(id = currentType.description),
                    style = TextStyle.Default
                ),
                onTypeChange = {
                    currentType = SpinnerPreviewType.find(it.position)
                }
            )
        }
    }
}


@Preview
@Composable
private fun MainActivityPreview() {
    DarkComposeUIDemoTheme {
        MainActivityContent()
    }
}
