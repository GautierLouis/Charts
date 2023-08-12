package com.louis.charts

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import com.google.android.material.R.drawable
import com.louis.charts.ui.theme.ChartsTheme
import com.louis.lib.PieChart
import com.louis.lib.pie.data.PieData

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ChartsTheme {
                // A surface container using the 'background' color from the theme
                Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background) {
                    Greeting("Android")
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    PieChart(
        data = listOf(
            PieData(13.0, Color.Red, drawable.ic_arrow_back_black_24),
            PieData(56.0, Color.Black, drawable.ic_arrow_back_black_24),
            PieData(21.0, Color.Yellow, drawable.ic_arrow_back_black_24),
            PieData(10.0, Color.Green, drawable.ic_arrow_back_black_24),
            PieData(100.0, Color.Magenta, drawable.ic_arrow_back_black_24),
        )
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    ChartsTheme {
        Greeting("Android")
    }
}