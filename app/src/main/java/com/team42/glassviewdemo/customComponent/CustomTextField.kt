package com.team42.glassviewdemo.customComponent

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlin.collections.any
import kotlin.text.isEmpty

/**
 * Project: ComposeCustomViews
 * File: CustomTextField.kt
 * Created By: ANIL KUMAR on 7/22/2025
 * Copyright © 2025 Team42. All rights reserved.
 **/

@Composable
fun CustomTextField(
    modifier: Modifier = Modifier,
    keyBoardOptions: KeyboardOptions = remember { KeyboardOptions.Default },
    keyboardActions: KeyboardActions = remember { KeyboardActions.Default },
    visualTransformation: VisualTransformation = remember { VisualTransformation.None },
    backgroundColor: Color = MaterialTheme.colorScheme.background,
    readonly: Boolean = false,
    borderWidth: Dp = 1.5.dp,
    borderColor: Color = MaterialTheme.colorScheme.onBackground,
    cursorColor: Color = MaterialTheme.colorScheme.onBackground,
    textColor: Color = MaterialTheme.colorScheme.onBackground,
    leadingIcon: @Composable (() -> Unit)? = null,
    trailingIcon: @Composable (() -> Unit)? = null,
    textAlignment: TextAlign = TextAlign.Start,
    isRoundedCorner: Boolean = false,
    topStartRadius: Dp = 0.dp,
    topEndRadius: Dp = 0.dp,
    bottomStartRadius: Dp = 0.dp,
    bottomEndRadius: Dp = 0.dp,
    maxLines: Int,
    maxLength: Int,
    singleLine: Boolean = false,
    placeHolder: String = "Placeholder",
    fontSize: TextUnit = 20.sp,
    isError: Boolean = false,
    errorText: String = "",
    inputWrapper: String,
    onValueChange: (String) -> Unit
) {
    var fieldText by rememberSaveable { mutableStateOf(inputWrapper) }
    require(
        !isRoundedCorner ||
                listOf(
                    topStartRadius,
                    topEndRadius,
                    bottomStartRadius,
                    bottomEndRadius
                ).any { it > 0.dp }
    ) {
        "Rounded corners are enabled, but no corner radius was provided."
    }

    val shape = if (isRoundedCorner) {
        RoundedCornerShape(
            topStart = topStartRadius,
            topEnd = topEndRadius,
            bottomStart = bottomStartRadius,
            bottomEnd = bottomEndRadius
        )
    } else {
        RectangleShape
    }
    BasicTextField(
        modifier = modifier
            .background(backgroundColor, shape)
            .fillMaxWidth()
            .border(
                width = borderWidth,
                shape = shape,
                color = if (isError) Color.Red else borderColor,
            ),
        readOnly = readonly,
        visualTransformation = visualTransformation,
        keyboardOptions = keyBoardOptions,
        keyboardActions = keyboardActions,
        value = fieldText,
        onValueChange = {
            if (it.length <= maxLength) {
                fieldText = it
                onValueChange(it)
            }
        },
        maxLines = if (singleLine) 1 else maxLines,
        singleLine = singleLine,
        cursorBrush = SolidColor(cursorColor),
        textStyle = LocalTextStyle.current.copy(
            color = textColor,
            textAlign = textAlignment,
            fontSize = fontSize
        ),
        decorationBox = { innerTextField ->
            Row(
                modifier = Modifier
                    .background(backgroundColor,shape)
                    .border(
                        width = borderWidth,
                        shape = shape,
                        color = if (isError) Color.Red else borderColor,
                    ),
                verticalAlignment = Alignment.CenterVertically
            ) {
                if (leadingIcon != null) {
                    Box(modifier = Modifier.padding(start = 8.dp, end = 8.dp)) {
                        leadingIcon()
                    }
                }
                Box(
                    modifier = Modifier
                        .weight(1f)
                        .padding(start = 8.dp, end = 8.dp),
                ) {
                    if (fieldText.isEmpty()) {
                        Text(
                            modifier = Modifier.padding(start = 4.dp),
                            text = placeHolder,
                            style = LocalTextStyle.current.copy(
                                color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.3f),
                                fontSize = fontSize,
                            )
                        )
                    }
                    innerTextField()
                }
                if (trailingIcon != null) {
                    Box(modifier = Modifier.padding(start = 8.dp, end = 8.dp)) {
                        trailingIcon()
                    }
                }
            }
        }
    )
    if (isError) {
        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.Start,
            verticalArrangement = Arrangement.Center

        ) {
            Text(
                modifier = Modifier.fillMaxWidth(),
                style = TextStyle(fontSize = 14.sp),
                text = errorText,
                color = Color.Red,
                textAlign = TextAlign.Start
            )
        }
    }
}
