package com.example.parcial1pdm.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults.topAppBarColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.parcial1pdm.models.ItemOrden
import com.example.parcial1pdm.models.Producto
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun OrderScreen(
    modifier: Modifier = Modifier,
    orden: List<ItemOrden>,
    isOrderConfirmed: Boolean,
    onNavigate:() -> Unit,
    onRemove:(Producto) -> Unit,
    onConfirm:() -> Unit
) {
    val total = orden.sumOf { it.producto.precio * it.cantidad }

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            TopAppBar(
                title = { Text(text = "Orden", fontWeight = FontWeight.Bold) },
                colors = topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                    titleContentColor = MaterialTheme.colorScheme.primary,
                )
            )
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier.padding(innerPadding),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Button(
                onClick = onNavigate,
                modifier = Modifier.padding(8.dp).align(Alignment.Start)
            ) {
                Text("Volver al menú")
            }
            Spacer(Modifier.height(16.dp))
            Surface(
                modifier = Modifier.fillMaxWidth().padding(8.dp),
                shape = RoundedCornerShape(8.dp),
                tonalElevation = 8.dp
            ) {
                LazyColumn(Modifier
                    .fillMaxWidth()
                    .padding(8.dp)) {
                    item {
                        Row(Modifier
                            .fillMaxWidth()
                            .padding(8.dp)) {
                            Text("Producto", Modifier.weight(4f))
                            Text("Cant.", Modifier.weight(1.5f))
                            Text("Precio", Modifier.weight(2f))
                            Text("Subtotal", Modifier.weight(2f))
                            Spacer(Modifier.weight(1f))
                        }
                    }
                    items(orden) {
                        if(it.cantidad > 0) {
                            val subtotal = it.producto.precio * it.cantidad
                            Row(Modifier.fillMaxWidth()) {
                                Text(it.producto.nombre, Modifier.weight(4f))
                                Text(it.cantidad.toString(), Modifier.weight(1.5f),
                                    textAlign = TextAlign.Center
                                )
                                Text("$${it.producto.precio}", Modifier.weight(2f))
                                Text("$$subtotal", Modifier.weight(2f))
                                Button(
                                    onClick = { onRemove(it.producto) },
                                    modifier = Modifier.weight(1f).size(24.dp),
                                    contentPadding = PaddingValues(1.dp)
                                ) {
                                    Text("x")
                                }
                            }
                        }
                    }
                }
            }
            Spacer(Modifier.height(16.dp))
            Text(text = "Total: $$total", fontWeight = FontWeight.Bold, fontSize = 24.sp)
            Spacer(Modifier.height(16.dp))
            if(total > 0.0) {
                Button(onConfirm) { Text("Confirmar orden") }
            }
            if(isOrderConfirmed) {
                Spacer(Modifier.height(32.dp))
                Text("La orden fué realizada exitosamente!")
            }
        }
    }
}