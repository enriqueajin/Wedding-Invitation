import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.jetbrains.compose.resources.*
import weddinginvitation.composeapp.generated.resources.*
import weddinginvitation.composeapp.generated.resources.Res
import weddinginvitation.composeapp.generated.resources.header
import weddinginvitation.composeapp.generated.resources.josephsophia

@Composable
fun Invitation() {
    Box(modifier = Modifier.fillMaxSize()) {
        Header()
        Body()
        Footer()
    }
}

@OptIn(ExperimentalResourceApi::class)
@Composable
fun Header() {
    Box(modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.TopCenter) {
        Image(
            painter = painterResource(Res.drawable.header),
            contentDescription = "Wedding Header",
            modifier = Modifier.height(300.dp),
            contentScale = ContentScale.Crop
        )
        Column(
            verticalArrangement = Arrangement.SpaceAround,
            modifier = Modifier.height(300.dp)
        ) {
            Column {
                Text(
                    text = "Nuestra Boda",
                    fontSize = 54.sp,
                    fontFamily = FontFamily(
                        Font(
                            resource = Res.font.josephsophia,
                            weight = FontWeight.Bold,
                            style = FontStyle.Normal,
                        )
                    ),
                )
                Text(
                    text = "Carlos Ajín y Diana Guillén",
                    modifier = Modifier.wrapContentSize(unbounded = true),
                    fontSize = 18.sp,
                    fontFamily = FontFamily.Default,
                )
            }
            Icon(
                painter = painterResource(Res.drawable.arrow_down),
                "Flecha hacia abajo",
                tint = Color.White,
                modifier = Modifier
                    .size(20.dp)
                    .align(Alignment.CenterHorizontally)
            )
            Spacer(modifier = Modifier.height(0.dp))
        }
    }
}

@Composable
fun Body() {

}

@Composable
fun Footer() {

}