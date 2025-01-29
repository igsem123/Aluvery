package br.com.app.src.main.kotlin.com.aluvery.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.datasource.LoremIpsum
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import br.com.app.src.main.kotlin.com.aluvery.R
import br.com.app.src.main.kotlin.com.aluvery.ui.theme.AluveryTheme
import br.com.app.src.main.kotlin.com.aluvery.ui.theme.Purple500
import br.com.app.src.main.kotlin.com.aluvery.ui.theme.Purple80

class TestComponents {
}

@Composable
fun composableChallenge() {
    val brush = Brush.verticalGradient(
        colors = listOf(
            Purple500,
            Purple80
        )
    )
    Surface(shape = RoundedCornerShape(8.dp), shadowElevation = 4.dp) {
        Row(
            modifier = Modifier
                .height(200.dp)
        ) {
            Box(
                Modifier
                    .fillMaxHeight()
                    .background(brush)
            ) {
                Image(
                    painter = painterResource(id = R.drawable.ic_launcher_foreground),
                    contentDescription = "Android Logo",
                    modifier = Modifier
                        .size(100.dp)
                        .offset(x = 50.dp)
                        .border(
                            width = 2.dp,
                            brush = Brush.verticalGradient(listOf(Purple80, Purple500)),
                            shape = CircleShape
                        )
                        .clip(shape = CircleShape)
                        .align(Alignment.Center)
                        .background(Color.LightGray)
                )
            }
            Spacer(modifier = Modifier.size(50.dp))
            Text(
                text = LoremIpsum(50).values.first(),
                overflow = TextOverflow.Ellipsis,
                fontSize = 16.sp,
                modifier = Modifier
                    .padding(20.dp)
                    .align(Alignment.CenterVertically)
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    AluveryTheme {
        composableChallenge()
    }
}