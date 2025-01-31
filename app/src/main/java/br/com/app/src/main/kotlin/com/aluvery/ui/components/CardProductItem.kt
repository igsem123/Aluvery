package br.com.app.src.main.kotlin.com.aluvery.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import br.com.app.src.main.kotlin.com.aluvery.R
import br.com.app.src.main.kotlin.com.aluvery.extensions.toBrazilianCurrency
import br.com.app.src.main.kotlin.com.aluvery.model.Product
import br.com.app.src.main.kotlin.com.aluvery.ui.theme.NeonOrange
import coil3.compose.AsyncImage
import java.math.BigDecimal

@Composable
fun CardProductItem(product: Product, modifier: Modifier = Modifier, elevation: Dp = 4.dp) {
    product.description?.let {
        var expanded by remember { mutableStateOf(false) }

        Card(
            modifier
                .width(330.dp)
                .heightIn(150.dp)
                .clickable { expanded = !expanded },
            elevation = CardDefaults.cardElevation(defaultElevation = elevation)
        ) {
            Column {
                AsyncImage(
                    model = product.image,
                    contentDescription = product.name,
                    modifier = Modifier
                        .height(100.dp)
                        .fillMaxWidth(),
                    placeholder = painterResource(id = R.drawable.placeholder),
                    contentScale = ContentScale.Crop
                )
                Box(
                    Modifier
                        .fillMaxWidth()
                        .background(color = NeonOrange)
                        .padding(16.dp)
                ) {
                    Column {
                        Text(
                            text = product.name,
                            color = Color.White
                        )
                        Text(
                            text = product.price.toBrazilianCurrency(),
                            color = Color.White
                        )
                    }
                }
                Column(
                    Modifier
                        .background(color = Color.LightGray)
                        .padding(16.dp)
                        .fillMaxWidth(),
                ) {
                    when (product.description.length > 100 && !expanded) {
                        true -> {
                            Text(
                                text = product.description,
                                maxLines = 3,
                                overflow = TextOverflow.Ellipsis
                            )
                        }

                        false -> {
                            Text(
                                text = product.description,
                                maxLines = Int.MAX_VALUE
                            )
                        }
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun ProductCardPreview() {
    CardProductItem(
        Product(
            name = "Product name",
            price = BigDecimal("18.89"),
            image = "https://www.example.com/image.jpg",
        )
    )
}

@Preview
@Composable
private fun ProductCardWithDescriptionPreview() {
    CardProductItem(
        Product(
            name = "Product name",
            price = BigDecimal("18.89"),
            image = "https://www.example.com/image.jpg",
            description = "Product description"
        )
    )
}