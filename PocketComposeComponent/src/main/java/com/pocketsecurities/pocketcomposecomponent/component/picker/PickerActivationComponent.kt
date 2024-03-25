package com.pocketsecurities.pocketcomposecomponent.component.picker

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.pocketsecurities.pocketcomposecomponent.color_414141
import com.pocketsecurities.pocketcomposecomponent.color_717071
import com.pocketsecurities.pocketcomposecomponent.component.dialog.NumberPickerDialog
import com.pocketsecurities.pocketcomposecomponent.component.text.PocketText
import com.pocketsecurities.pocketcomposecomponent.component.text.PocketTextConfig
import com.pocketsecurities.pocketcomposecomponent.extension.ClickableConfig
import com.pocketsecurities.pocketcomposecomponent.extension.clickableEffectConfig

@Composable
fun PickerActivationComponent(
    modifier: Modifier = Modifier,
    value: Int,
    isEnabled: Boolean = true,
    textConfig: PocketTextConfig = PocketTextConfig(),
    onValueSelected: (Int) -> Unit
) {
    val isDialogVisible = remember { mutableStateOf(false) }

    Box(
        modifier = modifier
            .width(80.dp)
            .height(30.dp)
            .clip(RoundedCornerShape(8.dp))
            .background(
                if (isEnabled) {
                    color_717071
                } else {
                    color_414141
                }
            )
            .clickableEffectConfig(
                config = ClickableConfig(
                    needRipple = false
                ),
                onClick = {
                    if (isEnabled) isDialogVisible.value = true
                }
            ),
        contentAlignment = Alignment.Center
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 3.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            PocketText(
                modifier = Modifier.weight(3f),
                config = textConfig
            )
            Icon(
                modifier = Modifier.weight(1f),
                imageVector = Icons.Default.KeyboardArrowDown,
                contentDescription = "",
                tint = if (isEnabled) Color.White else color_717071
            )
        }
    }

    NumberPickerDialog(
        isVisible = isDialogVisible.value,
        state = value,
        onValueChanged = {
            onValueSelected.invoke(it.toInt())
        },
        onConfirmed = {
            isDialogVisible.value = false
            onValueSelected.invoke(it.toInt())
        },
        onDismiss = {
            isDialogVisible.value = false
        }

    )
}

@Preview(
    widthDp = 300,
    heightDp = 300,
    showBackground = true
)
@Composable
private fun PickerActivationComponentPreview() {

    val tick = remember { mutableIntStateOf(1) }

    PickerActivationComponent(
        modifier = Modifier,
        value = tick.intValue,
        onValueSelected = {
            tick.intValue = it
        }
    )
}
