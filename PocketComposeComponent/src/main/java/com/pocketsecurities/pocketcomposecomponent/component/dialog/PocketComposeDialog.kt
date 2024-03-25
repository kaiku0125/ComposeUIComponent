package com.pocketsecurities.pocketcomposecomponent.component.dialog

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import com.pocketsecurities.pocketcomposecomponent.color_252525

@Composable
fun PocketComposeDialog(
    primaryColor: Color = MaterialTheme.colorScheme.primary,
    dialogBackgroundColor: Color = color_252525,
    positiveTextColor: Color? = null,
    title: String? = null,
    contentMessage: String = "",
    contentPaddingHorizontal: Dp = 40.dp,
    contentPaddingVertical: Dp = 8.dp,
    buttonShape: Shape = RoundedCornerShape(8.dp),
    positiveText: String? = "確定",
    negativeText: String? = "取消",
    onPositiveClick: (() -> Unit)? = null,
    onNegativeClick: (() -> Unit)? = null,
    positiveButton: (@Composable RowScope.() -> Unit)? = {
        Button(
            modifier = Modifier.weight(1f),
            colors = ButtonDefaults.buttonColors(
                containerColor = primaryColor,
                contentColor = Color.White
            ),
            shape = buttonShape,
            onClick = {
                onPositiveClick?.invoke()
            },
        ) {
            Text(
                text = positiveText ?: "",
                fontSize = 17.sp,
                fontWeight = FontWeight.Bold,
                maxLines = 1,
                color = positiveTextColor ?: Color.Unspecified,
            )
        }
    },
    negativeButton: (@Composable RowScope.() -> Unit)? = {
        Button(
            modifier = Modifier.weight(1f),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color.Transparent,
                contentColor = Color.White
            ),
            shape = buttonShape,
            border = BorderStroke(1.dp, primaryColor),
            onClick = {
                onNegativeClick?.invoke()
            }
        ) {
            Text(
                text = negativeText ?: "",
                color = primaryColor,
                fontSize = 17.sp,
                fontWeight = FontWeight.Bold
            )
        }
    },
    trailing: (@Composable ColumnScope.() -> Unit)? = null,
    content: (@Composable ColumnScope.() -> Unit) = {
        Box(
            modifier = Modifier
                .defaultMinSize(minHeight = 60.dp)
                .fillMaxWidth(),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = contentMessage,
                color = Color.White,
                fontSize = 15.sp,
                textAlign = TextAlign.Center
            )
        }
    }
) {
    Dialog(
        onDismissRequest = {},
        properties = DialogProperties(
            usePlatformDefaultWidth = false,
            dismissOnClickOutside = true,
            dismissOnBackPress = true,
        ),
    ) {
        Card(
            modifier = Modifier
                .padding(horizontal = 16.dp)
                .fillMaxWidth(),
            colors = CardDefaults.cardColors(
                containerColor = Color.Transparent,
                contentColor = Color.Transparent
            ),
            shape = RoundedCornerShape(8.dp)
        ) {
            Column {
                Column(
                    modifier = Modifier.background(color = dialogBackgroundColor),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    if (!title.isNullOrBlank()) {
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(50.dp)
                                .background(primaryColor), contentAlignment = Alignment.Center
                        )
                        {
                            Text(
                                text = title,
                                textAlign = TextAlign.Center,
                                color = positiveTextColor ?: Color.White,
                                fontWeight = FontWeight.Bold,
                                fontSize = 17.sp
                            )
                        }
                    }

                    Column(
                        modifier = Modifier.padding(
                            vertical = contentPaddingVertical,
                            horizontal = contentPaddingHorizontal
                        ), verticalArrangement = Arrangement.Center
                    ) {
                        content.invoke(this)
                        if (!negativeText.isNullOrBlank() || !positiveText.isNullOrBlank()) {
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(top = 8.dp),
                                horizontalArrangement = Arrangement.spacedBy(8.dp)
                            ) {
                                negativeText?.let {
                                    negativeButton?.invoke(this)

                                }
                                positiveText?.let {
                                    positiveButton?.invoke(this)
                                }
                            }
                        }
                    }
                }
                trailing?.invoke(this)
            }
        }
    }
}

@Composable
@Preview
fun PreviewBaseDialog() {
    PocketComposeDialog(
        title = "我是標題",
        contentMessage = "嗨\n我是第一行\n充數第二行"
    )
}

@Composable
@Preview
fun PreviewOneButtonDialog() {
    PocketComposeDialog(
        title = "我是標題",
        contentMessage = "嗨\n我是第一行\n充數第二行",
        positiveText = "我知道辣",
        negativeText = null
    )
}

@Composable
@Preview
fun PreviewTitleMessageDialog() {
    PocketComposeDialog(
        contentMessage = "嗨\n我是第一行\n充數第二行",
        positiveText = null,
        negativeText = null
    )
}