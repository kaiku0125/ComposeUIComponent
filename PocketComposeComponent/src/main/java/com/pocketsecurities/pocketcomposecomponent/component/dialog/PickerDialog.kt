package com.pocketsecurities.pocketcomposecomponent.component.dialog

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import androidx.constraintlayout.compose.ConstraintLayout
import com.pocketsecurities.pocketcomposecomponent.color_333333
import com.pocketsecurities.pocketcomposecomponent.color_e95356
import com.pocketsecurities.pocketcomposecomponent.component.picker.PickerComponent
import com.pocketsecurities.pocketcomposecomponent.component.text.PocketText
import com.pocketsecurities.pocketcomposecomponent.component.text.PocketTextConfig

@Composable
fun NumberPickerDialog(
    isVisible: Boolean = true,
    state : Int,
    onValueChanged: (String) -> Unit,
    onConfirmed: (String) -> Unit,
    onDismiss: () -> Unit
) {

    if (isVisible) {
        Dialog(
            onDismissRequest = {
                onDismiss.invoke()
            },
            properties = DialogProperties(
                usePlatformDefaultWidth = true,
                dismissOnClickOutside = true,
                dismissOnBackPress = true,
            ),
            content = {
                Card(
                    modifier = Modifier.fillMaxWidth(),
                    colors = CardDefaults.cardColors(
                        containerColor = Color.Transparent,
                        contentColor = Color.Transparent
                    ),
                    shape = RoundedCornerShape(8.dp)
                ) {


                    ConstraintLayout(
                        modifier = Modifier.background(color_333333)
                    ) {
                        val (title, divider, content, btnRow) = createRefs()

                        PocketText(
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(50.dp)
                                .constrainAs(title) {
                                    top.linkTo(parent.top)
                                    start.linkTo(parent.start)
                                    end.linkTo(parent.end)
                                },
                            config = PocketTextConfig(
                                value = "Tick 設定"
                            )
                        )
                        Divider(
                            modifier = Modifier
                                .fillMaxWidth()
                                .constrainAs(divider) {
                                    top.linkTo(title.bottom)
                                    start.linkTo(parent.start)
                                    end.linkTo(parent.end)
                                },
                            thickness = 1.5.dp,
                            color = Color.White
                        )

                        PickerComponent(
                            modifier = Modifier
                                .fillMaxWidth()
                                .background(color_333333)
                                .padding(horizontal = 30.dp)
                                .constrainAs(content) {
                                    top.linkTo(divider.bottom, margin = 10.dp)
                                    start.linkTo(parent.start)
                                    end.linkTo(parent.end)
                                },
                            value = state,
                            range = -10..10,
                            onValueChange = {
                                onValueChanged.invoke(it.toString())
                            },
                            textStyle = TextStyle(Color.White)
                        )

                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .constrainAs(btnRow) {
                                    top.linkTo(content.bottom, margin = 10.dp)
                                    start.linkTo(parent.start)
                                    end.linkTo(parent.end)
                                    bottom.linkTo(parent.bottom, margin = 10.dp)
                                }
                                .padding(horizontal = 40.dp),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            NegativeButton(
                                onClick = {
                                    onDismiss.invoke()
                                }
                            )

                            Spacer(modifier = Modifier.width(17.dp))

                            PositiveButton {
                                onConfirmed.invoke(state.toString())
                            }
                        }

                    }
                }
            }
        )
    }

}

@Composable
private fun RowScope.PositiveButton(
    onClick: () -> Unit
) {
    Button(
        modifier = Modifier.weight(1f),
        colors = ButtonDefaults.buttonColors(
            containerColor = color_e95356,
            contentColor = Color.White
        ),
        shape = RoundedCornerShape(8.dp),
        onClick = {
            onClick.invoke()
        },
    ) {
        Text(
            text = "確定",
            fontSize = 17.sp,
            fontWeight = FontWeight.Bold,
            maxLines = 1,
            color = Color.Unspecified,
        )
    }
}

@Composable
private fun RowScope.NegativeButton(
    onClick: () -> Unit
) {
    Button(
        modifier = Modifier.weight(1f),
        colors = ButtonDefaults.buttonColors(
            containerColor = Color.Transparent,
            contentColor = Color.White
        ),
        shape = RoundedCornerShape(8.dp),
        border = BorderStroke(1.dp, color_e95356),
        onClick = {
            onClick.invoke()
        }
    ) {
        Text(
            text = "取消",
            color = color_e95356,
            fontSize = 17.sp,
            fontWeight = FontWeight.Bold
        )
    }
}


@Preview
@Composable
private fun NumberPickerDialogPreview() {
    NumberPickerDialog(
        state = 0,
        onValueChanged = {

        },
        onConfirmed = {

        },
        onDismiss = {

        }
    )
}
