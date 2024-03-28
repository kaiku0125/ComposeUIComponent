package com.pocketsecurities.composeuidemo.preview.text

import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.pocketsecurities.composecomponent.R
import com.pocketsecurities.composecomponent.component.text.PocketText
import com.pocketsecurities.composecomponent.component.text.PocketTextConfig
import com.pocketsecurities.composecomponent.component.text.TextWithIcon
import com.pocketsecurities.composecomponent.component.text.TextWithIconConfig

@Preview
@Composable
private fun TextViewIconPreview() {
    TextWithIcon(
        topConfig = TextWithIconConfig(R.drawable.preview_ic_information),
        startConfig = TextWithIconConfig(R.drawable.preview_ic_information),
        bottomConfig = TextWithIconConfig(R.drawable.preview_ic_information),
        endConfig = TextWithIconConfig(R.drawable.preview_ic_information),
        content = {
            PocketText(
                textModifier = Modifier.padding(horizontal = 3.dp),
                config = PocketTextConfig(
                    value = "title"
                )
            )
        }
    )
}