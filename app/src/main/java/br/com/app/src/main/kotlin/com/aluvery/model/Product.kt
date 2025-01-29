package br.com.app.src.main.kotlin.com.aluvery.model

import androidx.annotation.DrawableRes
import br.com.app.src.main.kotlin.com.aluvery.R
import java.math.BigDecimal

class Product(
    val name: String,
    val price: BigDecimal,
    @DrawableRes val image: Int? = R.drawable.placeholder,
    val description: String? = null
)
