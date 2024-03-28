package com.pocketsecurities.pocketcomposecomponent.component.radio

import androidx.compose.animation.Crossfade
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.pocketsecurities.pocketcomposecomponent.color_9e9e9f
import com.pocketsecurities.pocketcomposecomponent.model.drawableProvider.localDrawableProvider


@Composable
fun PocketRadioIconComponent(
    modifier: Modifier = Modifier,
    isChecked: Boolean,
    isEnabled: Boolean,
    iconSize: Dp = 50.dp
) {
    Box(
        modifier = modifier.size(iconSize),
        contentAlignment = Alignment.Center
    ) {
        Crossfade(
            targetState = isChecked,
            label = ""
        ) { stateChecked ->
            if (stateChecked) {
                Icon(
                    modifier = Modifier.fillMaxSize(),
                    painter = painterResource(
                        id = if (isEnabled) {
                            localDrawableProvider().getCheckOnResource()
                        } else {
                            localDrawableProvider().getCheckOffResource()
                        }
                    ),
                    tint = Color.Unspecified,
                    contentDescription = ""
                )
            } else {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .clip(CircleShape)
                        .padding(3.5.dp)
                        .border(
                            border = BorderStroke(
                                width = 1.5.dp,
                                color = if (isEnabled) {
                                    MaterialTheme.colorScheme.onBackground
                                } else {
                                    color_9e9e9f
                                }
                            ),
                            shape = CircleShape
                        )
                )
            }
        }

    }

}



