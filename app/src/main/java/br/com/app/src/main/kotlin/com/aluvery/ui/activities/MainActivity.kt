package br.com.app.src.main.kotlin.com.aluvery.ui.activities

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.rounded.Home
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.FloatingActionButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import br.com.app.src.main.kotlin.com.aluvery.sampledata.sampleSections
import br.com.app.src.main.kotlin.com.aluvery.ui.screens.HomeScreen
import br.com.app.src.main.kotlin.com.aluvery.ui.states.HomeScreenUiState
import br.com.app.src.main.kotlin.com.aluvery.ui.theme.AluveryTheme
import br.com.app.src.main.kotlin.com.aluvery.ui.theme.NeonOrange
import br.com.app.src.main.kotlin.com.aluvery.ui.viewmodels.HomeScreenViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            App(
                onFabClick = {
                    startActivity(
                        Intent(this, ProductFormActivity::class.java)
                    )
                },
                onHomeClick =
                {
                    startActivity(
                        Intent(this, MainActivity::class.java)
                    )
                }
            ) {
                val viewModel by viewModels<HomeScreenViewModel>()
                HomeScreen(viewModel = viewModel)
            }
        }
    }
}

@Composable
fun App(
    onFabClick: () -> Unit = {},
    onHomeClick: () -> Unit = {},
    content: @Composable () -> Unit = {}
) {
    AluveryTheme {
        Surface {
            Scaffold(
                bottomBar = {
                    BottomAppBar(
                        actions = {
                            IconButton(modifier = Modifier
                                .padding(start = 8.dp)
                                .align(Alignment.CenterVertically),
                                onClick = { onHomeClick() }) {
                                Icon(
                                    Icons.Rounded.Home,
                                    contentDescription = "Botão Home",
                                    tint = NeonOrange,
                                    modifier = Modifier.size(34.dp)
                                )
                            }
                        },
                        floatingActionButton = {
                            FloatingActionButton(
                                onClick = { onFabClick() },
                                containerColor = NeonOrange,
                                elevation = FloatingActionButtonDefaults.elevation(1.dp),
                            ) {
                                Icon(Icons.Filled.Add, "Localized description", tint = Color.White)
                            }
                        }
                    )
                },
            ) { paddingValues ->
                Box(modifier = Modifier.padding(paddingValues)) {
                    content()
                }
            }
        }
    }
}

@Preview
@Composable
private fun AppPreview() {
    App {
        HomeScreen(
            state = HomeScreenUiState(searchText = "Hamburguer", sections = sampleSections),
        )
    }
}