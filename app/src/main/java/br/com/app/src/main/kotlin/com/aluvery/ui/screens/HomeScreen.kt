package br.com.app.src.main.kotlin.com.aluvery.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import br.com.app.src.main.kotlin.com.aluvery.model.Product
import br.com.app.src.main.kotlin.com.aluvery.sampledata.sampleProducts
import br.com.app.src.main.kotlin.com.aluvery.sampledata.sampleSections
import br.com.app.src.main.kotlin.com.aluvery.ui.components.CardProductItem
import br.com.app.src.main.kotlin.com.aluvery.ui.components.ProductsSection
import br.com.app.src.main.kotlin.com.aluvery.ui.components.SearchTextField

class HomeScreenUiState(searchText: String = "") {
    var text by mutableStateOf(searchText)
        private set

    val filteredProducts
        get() =
            if (text.isNotBlank()) {
                sampleProducts.filter { product ->
                    product.name.contains(text, ignoreCase = true) ||
                            product.description?.contains(text, ignoreCase = true) ?: false
                }
            } else emptyList()

    fun isShowSections(): Boolean {
        return text.isBlank()
    }

    val onSearchChange: (String) -> Unit = { searchText ->
        text = searchText
    }
}

@Composable
fun HomeScreen(
    sections: Map<String, List<Product>> = sampleSections,
    state: HomeScreenUiState = HomeScreenUiState(),
) {
    Column {
        val filteredProducts = remember(state.text) { state.filteredProducts }

        SearchTextField(
            searchText = state.text,
            onSearchChange = state.onSearchChange
        )

        LazyColumn(
            Modifier
                .fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(16.dp),
            horizontalAlignment = androidx.compose.ui.Alignment.CenterHorizontally,
            contentPadding = PaddingValues(bottom = 16.dp),
        ) {
            if (state.isShowSections()) {
                sections.forEach { (title, products) ->
                    item {
                        ProductsSection(title, products)
                    }
                }
            } else {
                items(filteredProducts) { p ->
                    CardProductItem(
                        product = p,
                        Modifier.padding(horizontal = 16.dp),
                        elevation = 4.dp,
                    )
                }
            }
        }
    }
}

@Preview(showSystemUi = true)
@Composable
private fun HomeScreenPreview() {
    HomeScreen(sampleSections, state = HomeScreenUiState(searchText = "Hamburguer"))
}