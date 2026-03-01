package com.mahmutalperenunal.c3paiddemo.core.ui.components

import androidx.compose.foundation.border
import androidx.compose.foundation.Image
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ChevronRight
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.mahmutalperenunal.c3paiddemo.core.ui.model.DpSize
import com.mahmutalperenunal.c3paiddemo.core.ui.model.ProgressCardDecor

@Composable
fun ProgressCard(
    title: String,
    value: String,
    titleColor: Color,
    valueColor: Color,
    gradientStart: Color,
    gradientEnd: Color,
    ctaBackground: Color,
    ctaIconTint: Color,
    modifier: Modifier = Modifier,
    onClick: (() -> Unit)? = null,
    decor: ProgressCardDecor? = null
) {
    // Card corner radius per Figma.
    val shape = RoundedCornerShape(8.dp)

    Box(
        modifier = modifier
            // Fixed card size to match the prototype layout exactly.
            .width(378.dp)
            .height(106.dp)
            // Draw shadow outside the clipped shape for a softer elevation.
            .shadow(elevation = 2.dp, shape = shape, clip = false)
            .clip(shape)
            // Subtle outline to separate the gradient card from the background.
            .border(1.dp, Color(0x14000000), shape)
            .background(
                brush = Brush.horizontalGradient(
                    colors = listOf(gradientStart, gradientEnd)
                )
            )
            // Only make the card clickable when a click handler is provided.
            .then(
                if (onClick != null) Modifier.clickable { onClick() } else Modifier
            )
    ) {
        // Optional decorative layers (ellipses/images/illustration) are positioned to match the design.
        decor?.let { d ->
            d.ellipses.forEach { e ->
                val ellipseModifier = Modifier
                    .offset(x = e.offset.x, y = e.offset.y)
                    .size(e.size.width, e.size.height)
                    .clip(CircleShape)

                val res = e.drawableRes
                if (res != null) {
                    Image(
                        painter = painterResource(id = res),
                        // Decorative background element (no accessibility label needed).
                        contentDescription = null,
                        contentScale = ContentScale.Fit,
                        modifier = ellipseModifier
                            .graphicsLayer(alpha = e.alpha)
                    )
                } else {
                    Box(
                        modifier = ellipseModifier
                            .background(e.color.copy(alpha = e.alpha))
                    )
                }
            }

            d.images.forEach { img ->
                Image(
                    painter = painterResource(id = img.drawableRes),
                    // Decorative overlay image positioned per design.
                    contentDescription = null,
                    contentScale = ContentScale.Fit,
                    modifier = Modifier
                        .offset(x = img.offset.x, y = img.offset.y)
                        .size(img.size.width, img.size.height)
                        .graphicsLayer(
                            rotationZ = img.rotationDeg,
                            alpha = img.alpha
                        )
                )
            }

            val resId = d.illustrationRes
            if (resId != null && d.illustrationSize != DpSize.Zero) {
                Image(
                    painter = painterResource(id = resId),
                    // Optional illustration asset (visual only in prototype).
                    contentDescription = null,
                    contentScale = ContentScale.Fit,
                    modifier = Modifier
                        .offset(x = d.illustrationOffset.x, y = d.illustrationOffset.y)
                        .size(d.illustrationSize.width, d.illustrationSize.height)
                        .clip(RoundedCornerShape(0.dp))
                        .then(
                            if (d.illustrationRotationDeg != 0f)
                                Modifier
                                    .graphicsLayer(rotationZ = d.illustrationRotationDeg)
                            else Modifier
                        )
                )
            }
        }
        // Foreground content layer (title/value + CTA icon).
        Row(
            modifier = Modifier
                .fillMaxSize()
                .padding(start = 24.dp, top = 8.dp, end = 12.dp, bottom = 8.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Column(
                verticalArrangement = Arrangement.Center
            ) {
                Text(
                    text = title,
                    style = MaterialTheme.typography.labelLarge,
                    color = titleColor
                )
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = value,
                    style = MaterialTheme.typography.displaySmall,
                    color = valueColor
                )
            }

            Box(
                modifier = Modifier
                    .width(32.dp)
                    .fillMaxHeight()
                    .padding(vertical = 12.dp),
                contentAlignment = Alignment.BottomEnd
            ) {
                Box(
                    modifier = Modifier
                        .size(32.dp)
                        .shadow(elevation = 2.dp, shape = CircleShape, clip = false)
                        .clip(CircleShape)
                        .background(ctaBackground)
                        .padding(start = 4.dp, top = 2.dp, end = 2.dp, bottom = 2.dp),
                    contentAlignment = Alignment.Center
                ) {
                    // Decorative icon; the card click action is conveyed via UI context.
                    Icon(
                        imageVector = Icons.Default.ChevronRight,
                        contentDescription = null,
                        tint = ctaIconTint,
                        modifier = Modifier.size(24.dp)
                    )
                }
            }
        }
    }
}