package com.louis.lib.pie.data

internal class PieDataFactory {
    private fun Double.calculateAngle(sum: Double): Double = div(sum).times(360f)

    private fun List<PieData>.accumulateAngle(until: Int, sumValue: Double) = foldIndexed(0.0) { i, acc, data ->
        if (i < until) acc + data.value.calculateAngle(sumValue)
        else acc
    }


    fun calculate(data: List<PieData>): List<PieSegment> {
        val sum: Double = data.sumOf { it.value }

        return data.mapIndexed { index, pieData ->
            val angle = pieData.value.calculateAngle(sum)
            val cumulativeAngle = data.accumulateAngle(index, sum)
            PieSegment(cumulativeAngle - 90.0, angle, pieData.color, pieData.icon)
        }
    }
}