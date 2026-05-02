package com.example.parcial1pdm.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil3.compose.AsyncImage
import com.example.parcial1pdm.models.Producto

@Composable
fun ProductCard(
    modifier: Modifier = Modifier,
    producto: Producto,
    cantidad: Int,
    onCardClick:() -> Unit
) {
    Card(
        modifier = Modifier.fillMaxWidth().padding(8.dp)
            .clickable { onCardClick() },
        shape = CardDefaults.elevatedShape
    ) {
        Row(
            modifier = Modifier.padding(8.dp).fillMaxWidth()
        ) {
            AsyncImage(
                model = producto.imagenUrl,
                contentDescription = producto.nombre,
                modifier = Modifier
                    .size(width = 80.dp, height = 80.dp)
                    .clip(RoundedCornerShape(8.dp)),
                contentScale = ContentScale.Crop
            )
            Column(modifier = Modifier.weight(3f)) {
                Text(text = producto.nombre, modifier.padding(8.dp))
                Text(text = "$" + producto.precio.toString(), modifier.padding(8.dp))
            }
            Box(
                modifier = Modifier.weight(1f),
                contentAlignment = Alignment.TopEnd
            ) { Text(text = if(cantidad > 0) "$cantidad" else "", fontSize = 24.sp) }
        }
    }
}