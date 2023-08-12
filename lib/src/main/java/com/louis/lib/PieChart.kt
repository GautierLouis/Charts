package com.louis.lib

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
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
import kotlin.math.sin

data class PieSettings(
    val strokeWidth: Dp,
)

@Composable
fun PieChart(
    modifier: Modifier = Modifier,
    settings: PieSettings = PieSettings(strokeWidth = 30.dp),
    data: List<PieData>
) {

    val segments = PieDataFactory().calculate(data)

    val icons = segments.map { ImageVector.vectorResource(id = it.icon) }
    val painter = icons.map { rememberVectorPainter(image = it) }

    Canvas(
        modifier = Modifier
            .padding(20.dp)
            .then(modifier)
    ) {

        val center = Offset(size.width / 2, size.height / 2)
        val radius = size.minDimension / 2.0f

        segments.forEachIndexed { index, data ->
            drawArc(
                color = data.color,
                startAngle = data.startAngle.toFloat(),
                sweepAngle = data.sweepAngle.toFloat(),
                useCenter = false,
                topLeft = Offset(center.x - radius, center.y - radius),
                size = Size(radius * 2, radius * 2),
                style = Stroke(settings.strokeWidth.toPx()),
            )
            val iconX =
                center.x + radius * cos(Math.toRadians((data.startAngle + data.sweepAngle / 2))).toFloat() - 24.dp.toPx() / 2
            val iconY =
                center.y + radius * sin(Math.toRadians((data.startAngle + data.sweepAngle / 2))).toFloat() - 24.dp.toPx() / 2

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