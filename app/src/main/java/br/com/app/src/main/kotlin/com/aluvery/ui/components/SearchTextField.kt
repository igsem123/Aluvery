package br.com.app.src.main.kotlin.com.aluvery.ui.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import br.com.app.src.main.kotlin.com.aluvery.ui.theme.NeonOrange

@Composable
fun SearchTextField(
    searchText: String = "",
    onSearchChange: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    OutlinedTextField(
        value = searchText,
        onValueChange = { newValue ->
            text = newValue
        },
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        shape = RoundedCornerShape(100),
        leadingIcon = {
            Icon(
                imageVector = Icons.Default.Search,
                contentDescription = "Ícone de lupa",
                tint = Color.Gray
            )
        },
        colors = OutlinedTextFieldDefaults.colors(
            focusedBorderColor = NeonOrange,
            unfocusedBorderColor = Color.Gray,
            focusedLabelColor = NeonOrange,
            cursorColor = NeonOrange,
            unfocusedLabelColor = Color.Gray
        ),
        label = { Text("Produto") },
        placeholder = { Text("O que você procura?") },
    )
}