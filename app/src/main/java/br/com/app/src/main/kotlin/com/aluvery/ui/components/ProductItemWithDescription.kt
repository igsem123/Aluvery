package br.com.app.src.main.kotlin.com.aluvery.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import br.com.app.src.main.kotlin.com.aluvery.R
import br.com.app.src.main.kotlin.com.aluvery.extensions.toBrazilianCurrency
import br.com.app.src.main.kotlin.com.aluvery.model.Product

@Composable
fun ProductItemWithDescription(product: Product) {
    Surface(
        shape = RoundedCornerShape(15.dp),
        shadowElevation = 4.dp
    ) {
        Column(
            Modifier
                .height(265.dp)
                .width(200.dp)
                .verticalScroll(rememberScrollState())
        ) {
            val imageSize = 100.dp
            Box(
                modifier = Modifier
                    .height(imageSize)
                    .background(
                        brush = Brush.horizontalGradient(
                            colors = listOf(
                                Color.Red,
                                Color.Yellow
                            )
                        )
                    )
                    .fillMaxWidth()
            ) {
                product.image?.let { painterResource(id = it) }?.let {
                    Image(
                        painter = it,
                        contentDescription = "Imagem do Produto",
                        Modifier
                            .size(imageSize)
                            .offset(y = imageSize / 2)
                            .clip(shape = CircleShape)
                            .background(Color.White)
                            .align(alignment = Alignment.BottomCenter),
                        contentScale = ContentScale.Crop
                    )
                }
            }
            Spacer(modifier = Modifier.height(imageSize / 2))
            Column(
                Modifier
                    .padding(16.dp)
                    .height(75.dp)
            ) {
                Text(
                    text = product.name,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.W700,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis
                )
                Text(
                    text = product.price.toBrazilianCurrency(),
                    Modifier.padding(top = 8.dp),
                    fontSize = 14.sp,
                    fontWeight = FontWeight.W400
                )
            }
            if (product.description?.isNotEmpty() == true) {
                Text(
                    text = product.description,
                    Modifier
                        .background(color = Color(0xFFFF5C00))
                        .padding(16.dp)
                        .fillMaxWidth(),
                    fontSize = 14.sp,
                    fontWeight = FontWeight.W400,
                    color = Color.White
                )
            }
        }
    }
}

@Preview
@Composable
private fun ProductItemWithDescriptionPreview() {
    ProductItemWithDescription(
        product = Product(
            "Produto 1",
            (14.00).toBigDecimal(),
            R.drawable.ic_launcher_foreground,
            "Descrição do Produto 1"
        )
    )
}