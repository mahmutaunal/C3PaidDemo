package com.mahmutalperenunal.c3paiddemo.core.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.border
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.mahmutalperenunal.c3paiddemo.R
import com.mahmutalperenunal.c3paiddemo.core.ui.theme.NavPillBg
import com.mahmutalperenunal.c3paiddemo.core.ui.theme.NavProgressTint
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.ui.draw.clip

@Composable
fun BottomPillNav(
    onHomeClick: () -> Unit,
    onProgressClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .height(100.dp),
        contentAlignment = Alignment.TopCenter
    ) {
        // Very large radius to create a true “pill” shape regardless of height.
        val pillShape = RoundedCornerShape(1000.dp)

        Row(
            modifier = Modifier
                .padding(top = 12.dp)
                // Fixed dimensions to match the Figma pill size/spacing exactly.
                .size(width = 195.dp, height = 72.dp)
                // Shadow is drawn outside the clipped pill to match the soft elevation in the design.
                .shadow(elevation = 2.dp, shape = pillShape, clip = false)
                // Ensure the pill background is clipped to the pill shape (prevents square background artifacts).
                .clip(pillShape)
                // Subtle outline to separate the pill from the background (per design).
                .border(width = 1.dp, color = Color(0x14000000), shape = pillShape)
                .background(color = NavPillBg)
                .padding(horizontal = 24.dp, vertical = 12.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Selected tab drives icon/text tint to reflect active state.
            NavItem(
                label = "Home",
                iconTint = NavProgressTint,
                textColor = NavProgressTint,
                icon = { _ -> NavPngIcon(resId = R.drawable.ic_nav_home_filled) },
                onClick = onHomeClick
            )

            NavItem(
                label = "Progress",
                iconTint = NavProgressTint,
                textColor = NavProgressTint,
                icon = { _ -> NavPngIcon(resId = R.drawable.ic_nav_progress_equalizer) },
                onClick = onProgressClick
            )
        }
    }
}

@Composable
private fun NavItem(
    label: String,
    iconTint: Color,
    textColor: Color,
    icon: @Composable (Color) -> Unit,
    onClick: () -> Unit
) {
    Column(
        modifier = Modifier
            .width(60.dp)
            .clickable { onClick() },
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box(modifier = Modifier.size(24.dp), contentAlignment = Alignment.Center) {
            icon(iconTint)
        }
        Spacer(modifier = Modifier.height(4.dp))
        // Use theme typography for consistency; only adjust weight for selected/unselected emphasis.
        val base = MaterialTheme.typography.labelMedium
        val weight = FontWeight.Medium

        Text(
            text = label,
            style = base.copy(fontWeight = weight),
            color = textColor
        )
    }
}

@Composable
private fun NavPngIcon(
    resId: Int,
    modifier: Modifier = Modifier
) {
    Image(
        painter = painterResource(id = resId),
        // Decorative icon (label text conveys meaning).
        contentDescription = null,
        contentScale = ContentScale.Fit,
        modifier = modifier.size(24.dp)
    )
}