package com.example.parcial1pdm.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults.topAppBarColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.parcial1pdm.models.ItemOrden
import com.example.parcial1pdm.models.Producto
import com.example.parcial1pdm.ui.components.ProductCard

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MenuScreen(
    modifier: Modifier = Modifier,
    orden: List<ItemOrden>,
    onNavigate:() -> Unit,
    onCardClick:(Producto) -> Unit
) {
    val totalItems = orden.sumOf { it.cantidad }

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            TopAppBar(
                title = { Text(text = "Menú", fontWeight = FontWeight.Bold) },
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
                onClick = { onNavigate() },
                modifier = Modifier.padding(8.dp).align(Alignment.End)
            ) {
                Text("Ver orden [$totalItems]")
            }
            LazyColumn(modifier.padding(8.dp)) {
                items(orden) {
                    ProductCard(
                        modifier,
                        it.producto,
                        it.cantidad,
                        { onCardClick(it.producto) }
                    )
                }
            }
        }
    }
}