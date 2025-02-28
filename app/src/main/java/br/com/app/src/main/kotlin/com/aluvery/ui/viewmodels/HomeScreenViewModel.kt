package br.com.app.src.main.kotlin.com.aluvery.ui.viewmodels

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import br.com.app.src.main.kotlin.com.aluvery.dao.ProductDao
import br.com.app.src.main.kotlin.com.aluvery.model.Product
import br.com.app.src.main.kotlin.com.aluvery.sampledata.sampleCandies
import br.com.app.src.main.kotlin.com.aluvery.sampledata.sampleDrinks
import br.com.app.src.main.kotlin.com.aluvery.sampledata.sampleProducts
import br.com.app.src.main.kotlin.com.aluvery.ui.states.HomeScreenUiState

class HomeScreenViewModel : ViewModel() {

    private val dao = ProductDao() // Inicializa o DAO de produtos

    var uiState: HomeScreenUiState by mutableStateOf(HomeScreenUiState(
        sections = mapOf(
            "Destaques" to dao.products().take(5),
            "Todos os produtos" to dao.products(),
            "Bebidas" to sampleDrinks,
            "Doces" to sampleCandies
        ),
        onSearchChange = {
            uiState = uiState.copy(
                searchText = it,
                filteredProducts = filteredProducts(it)
            )
        }
    ))
        private set

    private fun containsInNameOrDescription(text: String) = { product: Product ->
        product.name.contains(
            text,
            ignoreCase = true
        ) || product.description?.contains(
            text,
            ignoreCase = true
        ) == true
    }

    private fun filteredProducts(text: String): List<Product> {
        return if (text.isNotBlank()) {
            sampleProducts.filter(containsInNameOrDescription(text)) +
                    dao.products().filter(containsInNameOrDescription(text))
        } else emptyList()
    }
}