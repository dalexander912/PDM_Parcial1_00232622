package com.example.parcial1pdm

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.parcial1pdm.models.itemPedido
import com.example.parcial1pdm.ui.screens.MenuScreen
import com.example.parcial1pdm.ui.screens.OrderScreen
import com.example.parcial1pdm.ui.theme.Parcial1PDMTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Parcial1PDMTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    OrderUpApp(
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@Composable
fun OrderUpApp(modifier: Modifier = Modifier) {
    var orden = rememberSaveable { mutableStateListOf(itemPedido(null, 0)) }
    var currentScreen by rememberSaveable { mutableIntStateOf(1) }

    when(currentScreen) {
        1 -> MenuScreen(modifier, {}, orden, {currentScreen = 2})
        2 -> OrderScreen(modifier, {currentScreen = 1}, orden)
    }

}

@Preview(showBackground = true)
@Composable
fun OrderUpAppPreview() {
    Parcial1PDMTheme {
        OrderUpApp()
    }
}