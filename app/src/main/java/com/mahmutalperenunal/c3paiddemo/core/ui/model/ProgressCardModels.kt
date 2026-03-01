package com.mahmutalperenunal.c3paiddemo.core.ui.model

import androidx.annotation.DrawableRes
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

// UI model describing optional decorative layers for a ProgressCard (ellipses, images, illustration).
data class ProgressCardDecor(
    val ellipses: List<EllipseDecor> = emptyList(),
    val images: List<ImageDecor> = emptyList(),
    @param:DrawableRes val illustrationRes: Int? = null,
    val illustrationSize: DpSize = DpSize.Zero,
    val illustrationOffset: DpOffset = DpOffset(0.dp, 0.dp),
    val illustrationRotationDeg: Float = 0f
)

// Describes a positioned/rotated drawable used as a decorative element.
data class ImageDecor(
    @param:DrawableRes val drawableRes: Int,
    val size: DpSize,
    val offset: DpOffset,
    val rotationDeg: Float = 0f,
    val alpha: Float = 1f
)

// Describes an ellipse background element (solid color or drawable) with position and alpha.
data class EllipseDecor(
    val size: DpSize,
    val offset: DpOffset,
    @param:DrawableRes val drawableRes: Int? = null,
    val color: Color = Color.Transparent,
    val alpha: Float = 1f
)

// Simple DP-based offset model to keep layout math explicit and design-driven.
data class DpOffset(val x: Dp, val y: Dp)
// DP-based size model used to match exact Figma measurements.
data class DpSize(val width: Dp, val height: Dp) {
    companion object {
        val Zero = DpSize(0.dp, 0.dp)
    }
}