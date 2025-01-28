package com.example.datastorelesson

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.example.datastorelesson.ui.theme.DataStoreLessonTheme
import com.example.datastorelesson.ui.theme.Red

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val dataStoreManager = DataStoreManager(this)

        setContent {
            DataStoreLessonTheme {
                val bgColorState = remember { mutableStateOf(Color(Red.value)) }
                val textSizeState = remember { mutableIntStateOf(40) }

                LaunchedEffect(key1 = true) {
                    dataStoreManager.getSettings().collect { settings ->
                        bgColorState.value = Color(settings.bgColor)
                        textSizeState.intValue = settings.textSize
                    }
                }

                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = bgColorState.value
                ) {
                    MainScreen(dataStoreManager, textSizeState)
                }
            }
        }
    }
}



