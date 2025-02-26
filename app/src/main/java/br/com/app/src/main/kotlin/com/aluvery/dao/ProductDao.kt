package br.com.app.src.main.kotlin.com.aluvery.dao

import androidx.compose.runtime.mutableStateListOf
import br.com.app.src.main.kotlin.com.aluvery.model.Product
import br.com.app.src.main.kotlin.com.aluvery.sampledata.sampleProducts

class ProductDao {
    companion object {
        private val products = mutableStateListOf<Product>()
    }

    fun products() = products.toList()

    fun save(product: Product) {
        products.add(product)
    }
}