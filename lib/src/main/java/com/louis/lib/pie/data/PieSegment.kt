package com.louis.lib.pie.data

import androidx.annotation.DrawableRes
import androidx.compose.ui.graphics.Color

internal data class PieSegment(
    val startAngle: Double,
    val sweepAngle: Double,
    val color: Color,
    @DrawableRes val icon: Int
)