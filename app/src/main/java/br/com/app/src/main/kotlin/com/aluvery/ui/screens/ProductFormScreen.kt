package br.com.app.src.main.kotlin.com.aluvery.ui.screens

import android.icu.text.DecimalFormat
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
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
import br.com.app.src.main.kotlin.com.aluvery.model.Product
import br.com.app.src.main.kotlin.com.aluvery.ui.theme.AluveryTheme
import br.com.app.src.main.kotlin.com.aluvery.ui.theme.NeonOrange
import coil3.compose.AsyncImage
import java.math.BigDecimal

class ProductFormUiState(
    val url: String = "",
    val name: String = "",
    val price: String = "",
    val description: String = "",
    val isShowPreview: Boolean = url.isNotBlank(),
    val onUrlChange: (String) -> Unit = {},
    val onNameChange: (String) -> Unit = {},
    val onDescriptionChange: (String) -> Unit = {},
    val onPriceChange: (String) -> Unit = {},
)

@Composable
fun ProductFormScreen(
    onSaveClick: (Product) -> Unit = {}
) {
    var name by rememberSaveable { mutableStateOf("") }
    var url by rememberSaveable { mutableStateOf("") }
    var price by rememberSaveable { mutableStateOf("") }
    var description by rememberSaveable { mutableStateOf("") }
    val formatter = remember {
        DecimalFormat("#.##")
    }
    val isPriceError by remember(price) {
        mutableStateOf(price.toBigDecimalOrNull() == null)
    }

    ProductFormScreen(
        state = ProductFormUiState(
            url = url,
            name = name,
            price = price,
            description = description,
            onUrlChange = {
                url = it
            },
            onNameChange = {
                name = it
            },
            onPriceChange = {
                try {
                    price = formatter.format(BigDecimal(it))
                } catch (e: IllegalArgumentException) {
                    if (it.isBlank()) {
                        price = it
                    }
                }
            },
            onDescriptionChange = {
                description = it
            },
        ),
        onSaveClick = {
            val convertedPrice = try {
                BigDecimal(price)
            } catch (e: NumberFormatException) {
                BigDecimal.ZERO
            }
            val product = Product(
                name = name,
                image = url,
                price = convertedPrice,
                description = description
            )
            onSaveClick(product)
        }
    )
}

@Composable
fun ProductFormScreen(
    state: ProductFormUiState = ProductFormUiState(),
    onSaveClick: () -> Unit = {}
) {
    val url = state.url
    val name = state.name
    val price = state.price
    val description = state.description
    Column(
        Modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp)
            .verticalScroll(rememberScrollState()),
        verticalArrangement = Arrangement.spacedBy(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
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
            text = "Formulário do Produto",
            Modifier.fillMaxWidth(),
            fontSize = 28.sp,
        )
        if (state.isShowPreview) {
            AsyncImage(
                model = url, contentDescription = null,
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
            onValueChange = state.onUrlChange,
            Modifier.fillMaxWidth(),
            label = {
                Text(text = "Url da Imagem")
            },
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Uri,
                imeAction = ImeAction.Next
            )
        )
        TextField(
            value = name,
            onValueChange = state.onNameChange,
            Modifier.fillMaxWidth(),
            label = {
                Text(text = "Nome do Produto")
            },
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Text,
                imeAction = ImeAction.Next,
                capitalization = KeyboardCapitalization.Words
            )
        )

        TextField(
            value = price,
            onValueChange = state.onPriceChange,
            Modifier.fillMaxWidth(),
            label = {
                Text(text = "Preço do Produto")
            },
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Decimal,
                imeAction = ImeAction.Next
            )
        )

        TextField(
            value = description,
            onValueChange = state.onDescriptionChange,
            Modifier
                .fillMaxWidth()
                .heightIn(min = 100.dp),
            label = {
                Text(text = "Descrição do Produto")
            },
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Text,
                capitalization = KeyboardCapitalization.Sentences
            )
        )
        Button(
            onClick = onSaveClick,
            modifier = Modifier
                .width(200.dp),
            colors = ButtonColors(
                containerColor = NeonOrange,
                contentColor = Color.White,
                disabledContainerColor = Color.Gray,
                disabledContentColor = Color.Black
            )
        ) {
            Text(text = "Salvar")
        }
        Spacer(modifier = Modifier)
    }
}

@Preview(showBackground = true)
@Composable
fun ProductFormScreenPreview() {
    AluveryTheme {
        Surface {
            ProductFormScreen(state = ProductFormUiState())
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ProductFormScreenFilledPreview() {
    AluveryTheme {
        Surface {
            ProductFormScreen(
                state = ProductFormUiState(
                    url = "url teste",
                    name = "nome teste",
                    price = "123",
                    description = "descrição teste"
                )
            )
        }
    }
}