package com.louis.charts

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.google.android.material.R.drawable
import com.louis.charts.ui.theme.ChartsTheme
import com.louis.lib.PieChart
import com.louis.lib.pie.data.PieData

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ChartsTheme {
                Box(modifier = Modifier.background(Color.LightGray)) {
                    PieChart(
                        modifier = Modifier.size(200.dp),
                        data = listOf(
                            PieData(13.0, Color.Red, drawable.ic_arrow_back_black_24),
                            PieData(56.0, Color.Black, drawable.ic_arrow_back_black_24),
                            PieData(21.0, Color.Yellow, drawable.ic_arrow_back_black_24),
                            PieData(10.0, Color.Green, drawable.ic_arrow_back_black_24),
                            PieData(100.0, Color.Magenta, drawable.ic_arrow_back_black_24),
                        )
                    )
                }
            }
        }
    }
}