package com.example.parcial1pdm.ui.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.parcial1pdm.models.Producto

@Composable
fun ProductCard(modifier: Modifier = Modifier, producto: Producto, onCardClick:() -> Unit) {
    Card(
        modifier = modifier.fillMaxWidth().padding(8.dp),
        shape = CardDefaults.elevatedShape
    ) {
        Text(text = producto.nombre, modifier.padding(8.dp))
        Text(text = producto.precio.toString(), modifier.padding(8.dp))
        Button(onClick = {onCardClick()}) {
            Text(text = "+")
        }
    }
}