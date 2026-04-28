package com.example.parcial1pdm.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeContentPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.parcial1pdm.data.menu
import com.example.parcial1pdm.models.itemPedido
import com.example.parcial1pdm.ui.components.ProductCard

@Composable
fun MenuScreen(modifier: Modifier = Modifier, onCardClick:() -> Unit, orden: List<itemPedido>, onNavigate:() -> Unit) {
    Column(
        modifier = Modifier.safeContentPadding().fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Button(onClick = {onNavigate()}) {
            Text("Ver orden")
        }
        LazyColumn(modifier.padding(8.dp)) {
            items(menu) {
                ProductCard(modifier, it, onCardClick)
            }
        }
    }
}