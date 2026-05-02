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
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation3.runtime.entryProvider
import androidx.navigation3.runtime.rememberNavBackStack
import androidx.navigation3.ui.NavDisplay
import com.example.parcial1pdm.data.menu
import com.example.parcial1pdm.models.ItemOrden
import com.example.parcial1pdm.models.Producto
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
    val backStack = rememberNavBackStack(Routes.Menu)

    var orden = rememberSaveable { mutableStateListOf<ItemOrden>() }
    var isOrderConfirmed by rememberSaveable { mutableStateOf(false) }

    if(orden.isEmpty()) {
        for(producto in menu) {
            orden.add(ItemOrden(producto, 0))
        }
    }

    fun onCardClick(producto: Producto) {
        val index = orden.indexOfFirst { it.producto.id == producto.id }
        val item = orden[index]
        orden[index] = item.copy(cantidad = item.cantidad + 1)
    }
    fun onItemRemove(producto: Producto) {
        val index = orden.indexOfFirst { it.producto.id == producto.id }
        val item = orden[index]
        orden[index] = item.copy(cantidad = 0)
    }
    fun onOrderConfirmed() {
        orden.clear()
        isOrderConfirmed = true
    }

    NavDisplay(
        backStack = backStack,
        onBack = { backStack.removeLastOrNull() },
        entryProvider = entryProvider {
            entry<Routes.Menu> {
                MenuScreen(
                    modifier,
                    orden,
                    {backStack.add(Routes.Order)},
                    ::onCardClick
                )
            }
            entry<Routes.Order> {
                OrderScreen(
                    modifier,
                    orden,
                    isOrderConfirmed,
                    {backStack.removeLastOrNull(); isOrderConfirmed = false},
                    ::onItemRemove,
                    { onOrderConfirmed() }
                )
            }
        }
    )
}

@Preview(showBackground = true)
@Composable
fun OrderUpAppPreview() {
    Parcial1PDMTheme {
        OrderUpApp()
    }
}