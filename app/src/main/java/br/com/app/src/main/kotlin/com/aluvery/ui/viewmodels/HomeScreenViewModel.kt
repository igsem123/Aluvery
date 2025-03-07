package br.com.app.src.main.kotlin.com.aluvery.ui.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.app.src.main.kotlin.com.aluvery.dao.ProductDao
import br.com.app.src.main.kotlin.com.aluvery.model.Product
import br.com.app.src.main.kotlin.com.aluvery.sampledata.sampleCandies
import br.com.app.src.main.kotlin.com.aluvery.sampledata.sampleDrinks
import br.com.app.src.main.kotlin.com.aluvery.sampledata.sampleProducts
import br.com.app.src.main.kotlin.com.aluvery.ui.states.HomeScreenUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class HomeScreenViewModel : ViewModel() {

    private val dao = ProductDao() // Inicializa o DAO de produtos

    private val _uiState: MutableStateFlow<HomeScreenUiState> = MutableStateFlow(
        HomeScreenUiState()
    )

    val uiState get() = _uiState.asStateFlow()

    init {
        _uiState.update { currentState ->
            currentState.copy(
                onSearchChange = { text ->
                    _uiState.value = _uiState.value.copy(
                        searchText = text,
                        filteredProducts = filteredProducts(text)
                    )
                }
            )
        }
        viewModelScope.launch {
            dao.products().collect { products ->
                _uiState.value = _uiState.value.copy(
                    sections = mapOf(
                        "Todos os produtos" to products + sampleProducts,
                        "Doces" to sampleCandies,
                        "Bebidas" to sampleDrinks
                    ),
                    filteredProducts = filteredProducts(_uiState.value.searchText)
                )
            }
        }
    }

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
                    dao.products().value.filter(containsInNameOrDescription(text))
        } else emptyList()
    }
}