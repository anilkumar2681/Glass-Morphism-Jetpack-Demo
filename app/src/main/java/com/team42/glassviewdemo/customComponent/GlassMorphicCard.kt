package com.team42.glassviewdemo.customComponent

import android.graphics.RenderEffect
import android.graphics.Shader
import android.os.Build
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.asComposeRenderEffect
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

/**
 * Project: GlassViewDemo
 * File: GlassMorphicCard.kt
 * Created By: ANIL KUMAR on 9/25/2025
 * Copyright © 2025 Team42. All rights reserved.
 **/

@Composable
fun GlassMorphicCard(
    modifier: Modifier = Modifier,
    cornerRadius: Dp = 24.dp,
    blurRadius: Float = 20f,
    overlayColors: List<Color> = listOf(
        Color.White.copy(alpha = 0.25f),
        Color.White.copy(alpha = 0.05f)
    ),
    borderColor: Color = Color.White.copy(alpha = 0.4f),
    borderWidth: Dp = 1.dp,
    content: @Composable () -> Unit
) {
    Box(modifier = modifier) {
        Box(
            modifier = modifier
                .clip(RoundedCornerShape(cornerRadius))
                .then(
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
                        Modifier.graphicsLayer {
                            renderEffect = RenderEffect.createBlurEffect(
                                blurRadius,
                                blurRadius,
                                Shader.TileMode.CLAMP
                            ).asComposeRenderEffect()
                        }
                    } else Modifier
                )
        )
        Box(
            modifier = Modifier
                .padding(16.dp)
                .background(
                    brush = Brush.linearGradient(overlayColors),
                    shape = RoundedCornerShape(cornerRadius)
                )
                .border(borderWidth, borderColor, RoundedCornerShape(cornerRadius))
        ) {
            content()
        }
    }
}


@Preview(showBackground = true)
@Composable
fun GlassCardPreview() {
    GlassMorphicCard {}
}
