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
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.jetbrains.compose.resources.*
import weddinginvitation.composeapp.generated.resources.*
import weddinginvitation.composeapp.generated.resources.Res
import weddinginvitation.composeapp.generated.resources.header
import weddinginvitation.composeapp.generated.resources.josephsophia

@Composable
fun Invitation() {
    Column(modifier = Modifier.fillMaxSize()) {
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
    Box() {
        Column(modifier = Modifier.fillMaxWidth()) {
            InvitationText(modifier = Modifier.align(Alignment.CenterHorizontally))
            CountDown()
            Ceremony()
            Celebration()
            Dresscode()
            PhotoGallery()
            Gifts()
            Attendance()
        }
    }
}

@OptIn(ExperimentalResourceApi::class)
@Composable
fun InvitationText(modifier: Modifier) {
    Spacer(modifier = Modifier.height(45.dp))
    Text(
        text = "¡Están invitados!",
        modifier = modifier,
        fontSize = 44.sp,
        fontFamily = FontFamily(
            Font(
                resource = Res.font.josephsophia,
                weight = FontWeight.Bold,
                style = FontStyle.Normal,
            )
        ),
    )
    Text(
        text = "A lo largo de nuestro caminar durante estos 50 años ustedes han sido parte de muchos momentos y nos encantaría compartir esta bendición especial con ustedes nuestra familia y amigos especiales.",
        modifier = modifier.padding(10.dp),
        textAlign = TextAlign.Center,
        fontSize = 14.sp,
        fontFamily = FontFamily.Default
    )
}

@Composable
fun CountDown() {

}

@Composable
fun Ceremony() {
}

@Composable
fun Celebration() {
}

@Composable
fun Dresscode() {
}

@Composable
fun PhotoGallery() {
}

@Composable
fun Gifts() {
}

@Composable
fun Attendance() {
}

@Composable
fun Footer() {

}