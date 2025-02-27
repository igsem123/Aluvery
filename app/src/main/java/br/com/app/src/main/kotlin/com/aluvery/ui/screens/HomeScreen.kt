package br.com.app.src.main.kotlin.com.aluvery.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import br.com.app.src.main.kotlin.com.aluvery.model.Product
import br.com.app.src.main.kotlin.com.aluvery.sampledata.sampleSections
import br.com.app.src.main.kotlin.com.aluvery.ui.components.CardProductItem
import br.com.app.src.main.kotlin.com.aluvery.ui.components.ProductsSection
import br.com.app.src.main.kotlin.com.aluvery.ui.components.SearchTextField

class HomeScreenUiState(
    val sections: Map<String, List<Product>> = emptyMap(),
    val filteredProducts: List<Product> = emptyList(),
    val searchText: String = "",
    val onSearchChange: (String) -> Unit = {},
) {
    fun isShowSections(): Boolean {
        return searchText.isBlank()
    }
}

@Composable
fun HomeScreen(
    state: HomeScreenUiState = HomeScreenUiState(),
) {
    Column {
        val sections = remember(state.sections) { state.sections }

        SearchTextField(
            searchText = state.searchText,
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
                items(state.filteredProducts) { p ->
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
    HomeScreen(state = HomeScreenUiState(searchText = "Hamburguer", sections = sampleSections))
}