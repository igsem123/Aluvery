package br.com.app.src.main.kotlin.com.aluvery.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import br.com.app.src.main.kotlin.com.aluvery.sampledata.sampleProducts
import br.com.app.src.main.kotlin.com.aluvery.ui.components.ProductsSection
import br.com.app.src.main.kotlin.com.aluvery.ui.theme.AluveryTheme

@Composable
fun HomeScreen() {
    AluveryTheme {
        Surface {
            Column(
                Modifier
                    .fillMaxSize()
                    .verticalScroll(rememberScrollState())
                    .padding(vertical = 16.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                ProductsSection("Promoções", sampleProducts)
                ProductsSection("Mais vendidos", sampleProducts)
                ProductsSection("Doces", sampleProducts)
                ProductsSection("Refrigerantes", sampleProducts)
            }
        }
    }
}

@Preview(showSystemUi = true)
@Composable
private fun HomeScreenPreview() {
    HomeScreen()
}