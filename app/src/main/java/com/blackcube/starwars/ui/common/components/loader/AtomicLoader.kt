package com.blackcube.starwars.ui.common.components.loader

import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Box
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.geometry.center
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.PathOperation
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

/**
 * A composable function that displays an atomic-style rotating loader animation.
 *
 * @param modifier Modifier to be applied to the loader container.
 * @param color Color of the loader.
 * @param borderWidth Width of the loader's border.
 * @param cycleDuration Duration of one complete rotation cycle in milliseconds.
 */
@Composable
fun AtomicLoader(
    modifier: Modifier,
    color: Color = Color.White,
    borderWidth: Dp = 3.dp,
    cycleDuration: Int = 1000
) {
    val infiniteTransition = rememberInfiniteTransition("InfiniteAtomicLoaderTransition")

    val rotation by infiniteTransition.animateFloat(
        initialValue = 0f,
        targetValue = 360f,
        animationSpec = infiniteRepeatable(
            animation = tween(cycleDuration, easing = LinearEasing)
        ),
        label = "AtomicLoaderRotation"
    )

    Box(modifier) {
        RotatingCircle(
            modifier = Modifier.matchParentSize(),
            rotationX = 35f,
            rotationY = -45f,
            rotationZ = -90f + rotation,
            borderColor = color,
            borderWidth = borderWidth
        )
        RotatingCircle(
            modifier = Modifier.matchParentSize(),
            rotationX = 50f,
            rotationY = 10f,
            rotationZ = rotation,
            borderColor = color,
            borderWidth = borderWidth
        )
        RotatingCircle(
            modifier = Modifier.matchParentSize(),
            rotationX = 35f,
            rotationY = 55f,
            rotationZ = 90f + rotation,
            borderColor = color,
            borderWidth = borderWidth
        )
    }
}

/**
 * A composable function that draws a rotating circle with a border effect.
 *
 * @param modifier Modifier to be applied to the circle's drawing area.
 * @param rotationX Rotation angle around the X-axis in degrees.
 * @param rotationY Rotation angle around the Y-axis in degrees.
 * @param rotationZ Rotation angle around the Z-axis in degrees.
 * @param borderColor Color of the circle's border.
 * @param borderWidth Width of the circle's border.
 */
@Composable
private fun RotatingCircle(
    modifier: Modifier,
    rotationX: Float,
    rotationY: Float,
    rotationZ: Float,
    borderColor: Color,
    borderWidth: Dp
) {
    Canvas(
        modifier.graphicsLayer {
            this.rotationX = rotationX
            this.rotationY = rotationY
            this.rotationZ = rotationZ
            cameraDistance = 12f * density
        }
    ) {
        val mainCircle = Path().apply {
            addOval(Rect(size.center, size.minDimension / 2))
        }

        val clipCenter = Offset(size.width / 2f - borderWidth.toPx(), size.height / 2f)
        val clipCircle = Path().apply {
            addOval(Rect(clipCenter, size.minDimension / 2))
        }

        val path = Path().apply {
            op(mainCircle, clipCircle, PathOperation.Difference)
        }

        drawPath(path, borderColor)
    }
}