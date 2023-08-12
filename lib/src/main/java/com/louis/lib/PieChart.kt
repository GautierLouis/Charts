package com.louis.lib

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.drawscope.translate
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.louis.lib.pie.data.PieData
import com.louis.lib.pie.data.PieDataFactory
import kotlin.math.cos
import kotlin.math.roundToInt
import kotlin.math.sin


data class PieSettings(
    val size: Dp,
    val strokeWidth: Dp,
    val animDuration: Int,
)

@Composable
fun PieChart(
    modifier: Modifier,
    settings: PieSettings = PieSettings(200.dp, 50.dp, 1500),
    data: List<PieData>
) {

    val segments = PieDataFactory().calculate(data)

    val icons = segments.map { ImageVector.vectorResource(id = it.icon) }
    val painter = icons.map { rememberVectorPainter(image = it) }

    val animateFloat = segments.map { remember { Animatable(0f) } }

    LaunchedEffect(animateFloat) {
        animateFloat.forEachIndexed { index, anim ->
            val duration = segments[index].sweepAngle.div(360).times(settings.animDuration)
            anim.animateTo(
                targetValue = 1f,
                animationSpec = tween(
                    durationMillis = duration.roundToInt(),
                    easing = LinearEasing,
                )
            )
        }
    }



    Canvas(modifier = modifier.padding(settings.strokeWidth / 2)) {

        val center = Offset(size.width / 2, size.height / 2)
        val radius = size.minDimension / 2.0f

        segments.forEachIndexed { index, segment ->

            val iconSize = painter[index].intrinsicSize.toDpSize().width.toPx()
            val progress = segment.sweepAngle * animateFloat[index].value
            drawArc(
                color = segment.color,
                startAngle = segment.startAngle.toFloat(),
                sweepAngle = progress.toFloat(),
                useCenter = false,
                topLeft = Offset(center.x - radius, center.y - radius),
                size = Size(radius * 2, radius * 2),
                style = Stroke(settings.strokeWidth.toPx()),
            )

            val iconX =
                center.x + radius * cos(Math.toRadians(segment.relativeAngle)).toFloat() - iconSize / 2

            val iconY =
                center.y + radius * sin(Math.toRadians(segment.relativeAngle)).toFloat() - iconSize / 2

            if (progress >= (segment.sweepAngle / 2)) {
                with(painter[index]) {
                    translate(
                        left = iconX,
                        top = iconY
                    ) {
                        draw(
                            painter[index].intrinsicSize,
                            colorFilter = ColorFilter.tint(Color.White)
                        )
                    }
                }
            }
        }
    }
}