package br.com.app.src.main.kotlin.com.aluvery.ui.states

import br.com.app.src.main.kotlin.com.aluvery.model.Product

data class HomeScreenUiState(
    var sections: Map<String, List<Product>> = emptyMap(),
    val filteredProducts: List<Product> = emptyList(),
    val searchText: String = "",
    val onSearchChange: (String) -> Unit = {},
) {
    fun isShowSections(): Boolean {
        return searchText.isBlank()
    }
}