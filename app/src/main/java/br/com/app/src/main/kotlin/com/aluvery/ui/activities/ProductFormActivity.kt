package br.com.app.src.main.kotlin.com.aluvery.ui.activities

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import br.com.app.src.main.kotlin.com.aluvery.model.Product
import br.com.app.src.main.kotlin.com.aluvery.ui.theme.AluveryTheme

class ProductFormActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AluveryTheme {
                Surface {
                    ProductFormScreen()
                }
            }
        }
    }
}

@Composable
fun ProductFormScreen(modifier: Modifier = Modifier) {
    Column(
        Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("Criando o produto", Modifier.fillMaxWidth(), fontSize = 28.sp)
        var url by remember { mutableStateOf("") }
        TextField(
            value = url,
            onValueChange =
            {
                url = it
            },
            Modifier.fillMaxWidth(),
            label = { Text("URL da imagem") })

        var name by remember { mutableStateOf("") }
        TextField(
            value = name,
            onValueChange =
            {
                name = it
            },
            Modifier.fillMaxWidth(),
            label = { Text("Nome do produto") })

        var price by remember { mutableStateOf("") }
        TextField(
            value = price,
            onValueChange =
            {
                price = it
            },
            Modifier.fillMaxWidth(),
            label = { Text("Preço do produto") })

        var description by remember { mutableStateOf("") }
        TextField(
            value = description,
            onValueChange =
            {
                description = it
            },
            Modifier.fillMaxWidth()
                .heightIn(100.dp),
            label = { Text("Descrição do produto") }
        )

        Button(onClick = {
            val product = Product(
                name = name,
                price = price.toBigDecimal(),
                image = url,
                description = description
            )
            Log.i("ProductFormScreen", "ProductFormScreen: $product")
        }) {
            Text("Salvar")
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun ProductFormScreenPreview() {
    AluveryTheme {
        Surface {
            ProductFormScreen()
        }
    }
}