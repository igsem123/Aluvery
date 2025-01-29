package br.com.app.src.main.kotlin.com.aluvery.sampledata

import br.com.app.src.main.kotlin.com.aluvery.R
import br.com.app.src.main.kotlin.com.aluvery.model.Product
import java.math.BigDecimal

val sampleProducts = listOf(
    Product(
        name = "Hamburguer",
        price = BigDecimal("14.99"),
        image = R.drawable.burger,
        description = "Hamburguer 100% bovino, com bacon, alface, tomate e maionese."
    ),
    Product(
        name = "Pizza",
        price = BigDecimal("59.99"),
        image = R.drawable.pizza,
        description = "Pizza de 4 queijos, com borda de catupiry."
    ),
    Product(
        name = "Refrigerante",
        price = BigDecimal("4.99")
    ),
    Product(
        name = "Batata frita",
        price = BigDecimal("7.99"),
        image = R.drawable.fries,
        description = "Batata frita com tempero da casa."
    )
)