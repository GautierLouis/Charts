package com.louis.lib.pie

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
import androidx.compose.ui.graphics.drawscope.Fill
import androidx.compose.ui.graphics.drawscope.translate
import androidx.compose.ui.text.ExperimentalTextApi
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.drawText
import androidx.compose.ui.text.rememberTextMeasurer
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.louis.lib.PieSettings
import com.louis.lib.pie.data.PieData
import com.louis.lib.pie.data.PieDataFactory
import kotlin.math.cos
import kotlin.math.roundToInt
import kotlin.math.sin

@OptIn(ExperimentalTextApi::class)
@Composable
fun FullPieChart(
    modifier: Modifier = Modifier,
    settings: PieSettings = PieSettings(200.dp, strokeWidth = 30.dp, 0),
    data: List<PieData>
) {

    val segments = PieDataFactory().calculate(data)

    val animateFloat = segments.map { remember { Animatable(0f) } }

    val textMeasure = rememberTextMeasurer()

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

    Canvas(
        modifier = Modifier
            .padding(20.dp)
            .then(modifier)
    ) {

        val center = Offset(size.width / 2, size.height / 2)
        val radius = size.minDimension / 2.0f

        //TODO use provided icon
        val iconSize = 24.dp.toPx()

        segments.forEachIndexed { index, data ->
            drawArc(
                color = data.color,
                startAngle = data.startAngle.toFloat(),
                sweepAngle = data.sweepAngle.toFloat() * animateFloat[index].value,
                useCenter = true,
                topLeft = Offset(center.x - radius, center.y - radius),
                size = Size(radius * 2, radius * 2),
                style = Fill,
            )

            val iconX =
                center.x + radius / 2 * cos(Math.toRadians(data.relativeAngle)).toFloat() - iconSize / 2

            val iconY =
                center.y + radius / 2 * sin(Math.toRadians(data.relativeAngle)).toFloat() - iconSize / 2

            translate(
                left = iconX,
                top = iconY
            ) {
                drawText(
                    textMeasurer = textMeasure, text = "Text on Canvas!",
                    style = TextStyle(
                        fontSize = 8.sp,
                        color = Color.White
                    ),
                    topLeft = Offset.Zero
                )
            }
        }
    }
}