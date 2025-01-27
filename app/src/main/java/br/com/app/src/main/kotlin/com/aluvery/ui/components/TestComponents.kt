package br.com.app.src.main.kotlin.com.aluvery.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import br.com.app.src.main.kotlin.com.aluvery.ui.theme.AluveryTheme

class TestComponents {
}

@Composable
fun myFirstComposable() {
    Column(
        Modifier
        .background(color = Color.Blue)
        .padding(all = 16.dp)
    ) {
        Text(
            text = "Meu primeiro Texto!",
        )
        Text(
            text = "Meu segundo Texto!",
        )
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    AluveryTheme {
        myFirstComposable()
    }
}