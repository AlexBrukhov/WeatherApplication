package alexbrukhov.weatherapplication.app.presentation

import alexbrukhov.weatherapplication.R
import alexbrukhov.weatherapplication.app.data.WeatherModel
import alexbrukhov.weatherapplication.app.domain.getData
import alexbrukhov.weatherapplication.app.presentation.screens.MainCard
import alexbrukhov.weatherapplication.app.presentation.screens.TabLayout
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import alexbrukhov.weatherapplication.app.presentation.theme.WeatherApplicationTheme
import android.content.Context
import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import com.android.volley.Request
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley

const val API_KEY="aaca1c7c21e64e86aac121434221411"

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val currentDay =
            mutableStateOf(WeatherModel(
                "",
                "",
                "0.0",
                "",
                "",
                "0.0",
                "0.0",
                ""
            ))

        setContent {
            WeatherApplicationTheme {
                val daysList = remember {
                    mutableStateOf(listOf<WeatherModel>())
                }
                getData("Moscow", this, daysList, currentDay)
                Image(
                    painter = painterResource(id = R.drawable.weather_bg),
                    contentDescription = "im1",
                    modifier = Modifier
                        .fillMaxSize()
                        .alpha(0.8f),
                    contentScale = ContentScale.FillBounds
                )
                Column {
                    MainCard(currentDay)
                    TabLayout(daysList, currentDay)
                }
            }
        }
    }
}



