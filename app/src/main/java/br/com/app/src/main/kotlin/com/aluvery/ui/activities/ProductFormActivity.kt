package br.com.app.src.main.kotlin.com.aluvery.ui.activities

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ArrowBack
import androidx.compose.material.icons.rounded.Home
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import br.com.app.src.main.kotlin.com.aluvery.R
import br.com.app.src.main.kotlin.com.aluvery.dao.ProductDao
import br.com.app.src.main.kotlin.com.aluvery.model.Product
import br.com.app.src.main.kotlin.com.aluvery.ui.theme.AluveryTheme
import br.com.app.src.main.kotlin.com.aluvery.ui.theme.NeonOrange
import coil3.compose.AsyncImage
import java.math.BigDecimal

class ProductFormActivity : ComponentActivity() {

    private val dao = ProductDao() // Inicializa o DAO

    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AluveryTheme {
                Surface {
                    Scaffold (
                        topBar = {
                            TopAppBar(
                                title = {
                                    Text("Adicionar produto")
                                },
                                navigationIcon = {
                                    IconButton(onClick = {
                                        finish()
                                    }) {
                                        Icon(
                                            Icons.Rounded.ArrowBack,
                                            contentDescription = "Botão Para Voltar",
                                            tint = NeonOrange,
                                            modifier = Modifier.size(40.dp)
                                        )
                                    }
                                }
                            )
                        }
                    ) { paddingValues ->
                        Box(modifier = Modifier.padding(paddingValues)) {
                            ProductFormScreen(
                                onSaveClick = { product ->
                                    dao.save(product)
                                    Log.d("ProductFormActivity", "Produto salvo: $product")
                                    finish()
                                }
                            )
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun ProductFormScreen(modifier: Modifier = Modifier, onSaveClick: (Product) -> Unit = {}) {
    Column(
        Modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp)
            .verticalScroll(rememberScrollState()),
        verticalArrangement = Arrangement.spacedBy(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Spacer(modifier = Modifier)
        Icon(
            painter = painterResource(id = R.drawable.ic_app_food_vector),
            contentDescription = "Imagem do produto",
            Modifier
                .fillMaxWidth()
                .height(200.dp),
            tint = NeonOrange
        )
        Spacer(modifier = Modifier)
        Text(
            text = "Criando o produto",
            Modifier.fillMaxWidth(),
            fontSize = 28.sp
        )
        var url by remember { mutableStateOf("") }
        if (url.isNotBlank()) {
            AsyncImage(
                model = url, contentDescription = "Imagem do produto",
                Modifier
                    .fillMaxWidth()
                    .height(200.dp),
                contentScale = ContentScale.Crop,
                placeholder = painterResource(id = R.drawable.placeholder),
                error = painterResource(id = R.drawable.placeholder)
            )
        }
        TextField(
            value = url,
            onValueChange =
            {
                url = it
            },
            Modifier.fillMaxWidth(),
            label = { Text("URL da imagem") },
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Uri,
                imeAction = ImeAction.Next
            )
        )

        var name by remember { mutableStateOf("") }
        TextField(
            value = name,
            onValueChange =
            {
                name = it
            },
            Modifier.fillMaxWidth(),
            label = { Text("Nome do produto") },
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Text,
                imeAction = ImeAction.Next,
                capitalization = KeyboardCapitalization.Words
            )
        )

        var price by remember { mutableStateOf("") }
        var isPriceError by remember { mutableStateOf(false) }
        TextField(
            value = price,
            onValueChange = {
                isPriceError = try {
                    it.toBigDecimal()
                    false
                } catch (e: IllegalArgumentException) {
                    it.isNotEmpty()
                }
                price = it
            },
            Modifier.fillMaxWidth(),
            isError = isPriceError,
            label = {
                Text("Preço do produto")
            },
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Decimal,
                imeAction = ImeAction.Next
            )
        )

        if (isPriceError) {
            Text(
                text = "Preço deve ser um número decimal",
                color = MaterialTheme.colorScheme.error,
                style = MaterialTheme.typography.bodyMedium,
                modifier = Modifier.padding(start = 16.dp)
            )
        }

        var description by remember { mutableStateOf("") }
        TextField(
            value = description,
            onValueChange =
            {
                description = it
            },
            Modifier
                .fillMaxWidth()
                .heightIn(100.dp),
            maxLines = 4,
            label = { Text("Descrição do produto") },
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Text,
                capitalization = KeyboardCapitalization.Sentences,
            )
        )

        Button(
            onClick = {
                val convertedPrice = try {
                    price.toBigDecimal()
                } catch (e: NumberFormatException) {
                    BigDecimal.ZERO
                }
                val product = Product(
                    name = name,
                    price = convertedPrice,
                    image = url,
                    description = description
                )
                onSaveClick(product);
            },
            modifier = Modifier
                .width(200.dp),
            colors = ButtonColors(
                containerColor = NeonOrange,
                contentColor = Color.White,
                disabledContainerColor = Color.Gray,
                disabledContentColor = Color.Black
            )
        ) {
            Text("Salvar")
        }
        Spacer(modifier = Modifier)
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