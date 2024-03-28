package com.pocketsecurities.composeuidemo.preview.toggle

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.pocketsecurities.composeuidemo.ui.theme.ComposeUIDemoTheme
import com.pocketsecurities.pocketcomposecomponent.component.toggle.PocketSwitch

@Preview
@Composable
fun PocketSwitchPreview() {
    
    var checked by remember{ mutableStateOf(false) }

    ComposeUIDemoTheme {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.surface),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            PocketSwitch(
                checked = checked,
                size = 100.dp,
                onClick = {
                    checked = !checked
                }
            )

            Spacer(modifier = Modifier.height(20.dp))

            PocketSwitch(
                checked = checked,
                size = 35.dp,
                padding = 3.dp,
                onClick = {
                    checked = !checked
                }
            )
        }
    }
}