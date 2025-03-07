package br.com.app.src.main.kotlin.com.aluvery.ui.viewmodels

import android.icu.text.DecimalFormat
import androidx.lifecycle.ViewModel
import br.com.app.src.main.kotlin.com.aluvery.dao.ProductDao
import br.com.app.src.main.kotlin.com.aluvery.model.Product
import br.com.app.src.main.kotlin.com.aluvery.ui.states.ProductFormUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import java.math.BigDecimal

class ProductFormScreenViewModel : ViewModel() {

    private val dao = ProductDao()

    private val _uiState: MutableStateFlow<ProductFormUiState> = MutableStateFlow(
        ProductFormUiState()
    )

    val uiState get() = _uiState.asStateFlow()

    private val formatter = DecimalFormat("#.##")

    init {
        _uiState.update { currentState ->
            currentState.copy(
                onUrlChange = { url ->
                    _uiState.value = _uiState.value.copy(
                        url = url
                    )
                },
                onNameChange = { name ->
                    _uiState.value = _uiState.value.copy(
                        name = name
                    )
                },
                onPriceChange = { it ->
                    val price = try {
                        formatter.format(BigDecimal(it))
                    } catch (e: IllegalArgumentException) {
                        if (it.isBlank()) {
                            it
                        } else {
                            null
                        }
                    }

                    price?.let {
                        _uiState.value = _uiState.value.copy(
                            price = price
                        )
                    }
                },
                onDescriptionChange = { desc ->
                    _uiState.value = _uiState.value.copy(
                        description = desc
                    )
                }
            )
        }
    }

    fun save() {
        _uiState.value.run {

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

            // Salva o produto
            dao.save(product)
        }
    }
}