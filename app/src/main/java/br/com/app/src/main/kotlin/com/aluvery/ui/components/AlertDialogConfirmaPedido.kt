package br.com.app.src.main.kotlin.com.aluvery.ui.components

import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Fastfood
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import br.com.app.src.main.kotlin.com.aluvery.ui.theme.DarkOrange
import br.com.app.src.main.kotlin.com.aluvery.ui.theme.DarkRed
import br.com.app.src.main.kotlin.com.aluvery.ui.theme.LightOrange
import br.com.app.src.main.kotlin.com.aluvery.ui.theme.NeonOrange
import br.com.app.src.main.kotlin.com.aluvery.ui.theme.PastelOrange
import br.com.app.src.main.kotlin.com.aluvery.ui.theme.Platinum
import br.com.app.src.main.kotlin.com.aluvery.ui.theme.SmokyBlack

@Composable
fun AlertDialogConfirmaPedido(
    onConfirmButton: () -> Unit,
    onCancelButton: () -> Unit,
    setShowDialog: (Boolean) -> Unit,
    modifier: Modifier = Modifier
) {
    AlertDialog(
        onDismissRequest = { setShowDialog(false) },
        icon = {
            Icon(
                Icons.Filled.Fastfood,
                contentDescription = "Ícone que representa um pedido de hambúrguer e refrigerante.",
                tint = DarkOrange,
                modifier = Modifier
                    .size(44.dp)
            )
        },
        title = {
            Text(
                "Realizar o pedido?",
                color = SmokyBlack,
            )
        },
        text = {
            Text(
                "Clique no botão para confirmar o pedido. Ou, se deseja cancelar, clique em cancelar.",
                color = SmokyBlack,
            )
        },
        confirmButton = {
            TextButton(onClick = {
                onConfirmButton()
                setShowDialog(false)
            }) {
                Text(
                    "Confirmar",
                    color = DarkOrange
                )
            }
        },
        dismissButton = {
            TextButton(onClick = {
                onCancelButton()
                setShowDialog(false)
            }) {
                Text(
                    "Cancelar",
                    color = SmokyBlack
                )
            }
        },
        containerColor = Platinum,
        modifier = modifier
    )
}

@Preview
@Composable
private fun AlertDialogConfirmaPedidoPreview() {
    AlertDialogConfirmaPedido({}, {}, {})
}