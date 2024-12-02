package com.blackcube.starwars.ui.common.components.styled_box

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme.shapes
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.dp

@Composable
fun StyledBox(
    modifier: Modifier = Modifier,
    shape: Shape? = null,
    content: @Composable BoxScope.() -> Unit
) = Box(
    contentAlignment = Alignment.Center,
    content = content,
    modifier = Modifier
        .clip(shape ?: RoundedCornerShape(12.dp))
//        .border(1.dp, Color.DarkGray, shape ?: shapes)
        .then(modifier)
)