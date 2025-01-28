package com.example.datastorelesson

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.datastorelesson.ui.theme.Blue
import com.example.datastorelesson.ui.theme.Green
import kotlinx.coroutines.launch

@Composable
fun MainScreen(dataStoreManager: DataStoreManager, textSizeState: MutableState<Int>) {
    val coroutineScope = rememberCoroutineScope()
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize(0.5f)
                .wrapContentWidth(align = Alignment.CenterHorizontally)
                .wrapContentHeight(align = Alignment.CenterVertically)
        ) {
            Text(
                "Hello World",
                color = Color.Black,
                fontSize = textSizeState.value.sp
            )
        }
        Spacer(modifier = Modifier.height(10.dp))

        Button(
            onClick = {
                coroutineScope.launch{
                    dataStoreManager.saveSettings(
                        SettingsData(
                            20,
                            Blue.value.toLong()
                        )
                    )
                }
            }
        ) {
            Text("Blue")
        }
        Spacer(modifier = Modifier.height(10.dp))
        Button(
            onClick = {
                coroutineScope.launch {
                    dataStoreManager.saveSettings(
                        SettingsData(
                            30,
                            Green.value.toLong()
                        )
                    )
                }
            }
        ) {
            Text("Green")
        }
    }
}