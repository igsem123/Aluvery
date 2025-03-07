package br.com.app.src.main.kotlin.com.aluvery.ui.activities

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import br.com.app.src.main.kotlin.com.aluvery.ui.screens.ProductFormScreen
import br.com.app.src.main.kotlin.com.aluvery.ui.theme.AluveryTheme
import br.com.app.src.main.kotlin.com.aluvery.ui.theme.NeonOrange
import br.com.app.src.main.kotlin.com.aluvery.ui.viewmodels.ProductFormScreenViewModel

class ProductFormActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ProductFormScreen(
                finish = { finish() }
            ) {
                val viewModel: ProductFormScreenViewModel by viewModels()
                ProductFormScreen(
                    viewModel = viewModel,
                    onSaveClick = {
                        finish()
                    }
                )
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProductFormScreen(
    finish: () -> Unit = {},
    content: @Composable () -> Unit = {},
) {
    AluveryTheme {
        Surface {
            Scaffold(
                topBar = {
                    TopAppBar(
                        title = {
                            Text("Adicionar produto")
                        },
                        navigationIcon = {
                            IconButton(onClick = {
                                finish()
                            }) {
                                Icon(
                                    Icons.Rounded.ArrowBack,
                                    contentDescription = "Botão Para Voltar",
                                    tint = NeonOrange,
                                    modifier = Modifier.size(40.dp)
                                )
                            }
                        }
                    )
                }
            ) { paddingValues ->
                Box(modifier = Modifier.padding(paddingValues)) {
                    content()
                }
            }
        }
    }
}