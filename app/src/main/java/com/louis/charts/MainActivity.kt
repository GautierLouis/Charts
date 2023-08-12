package com.louis.charts

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.louis.charts.ui.theme.ChartsTheme
import com.louis.lib.PieChart
import com.louis.lib.pie.data.PieData


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ChartsTheme {
                Box(modifier = Modifier) {
                    PieChart(
                        modifier = Modifier.fillMaxSize(),
                        data = listOf(
                            PieData(13.0, Color.Red, R.drawable.ic_train_24),
                            PieData(56.0, Color.Black, R.drawable.ic_train_24),
                            PieData(21.0, Color.Yellow, R.drawable.ic_train_24),
                            PieData(10.0, Color.Green, R.drawable.ic_train_24),
                            PieData(100.0, Color.Magenta, R.drawable.ic_train_24),
                        )
                    )
                }
            }
        }
    }
}