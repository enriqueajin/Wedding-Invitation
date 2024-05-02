import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.datetime.*
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
            CountDown(modifier = Modifier.align(Alignment.CenterHorizontally))
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
fun CountDown(modifier: Modifier) {

    val weddingDateTime = LocalDateTime(
        year = 2024,
        monthNumber = 8,
        dayOfMonth = 31,
        hour = 18,
        minute = 0,
        second = 0
    )
    Spacer(modifier = Modifier.height(20.dp))
    Row(
        modifier = modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceAround
    ) {
        var daysState by rememberSaveable { mutableLongStateOf(0) }
        var hoursState by rememberSaveable { mutableLongStateOf(0) }
        var minutesState by rememberSaveable { mutableLongStateOf(0) }
        var secondsState by rememberSaveable { mutableLongStateOf(0) }

        CustomCountDownTimer.setTimer(
            weddingDate = weddingDateTime,
            onTick = { days, hours, minutes, seconds ->
                daysState = days
                hoursState = hours
                minutesState = minutes
                secondsState = seconds
            },
            onFinish = {
                // Not yet implemented
            }
        )

        val countDownList = listOf(
            TimeItem(time = daysState.toString(), label = "DÍAS"),
            TimeItem(time = hoursState.toString(), label = "HORAS"),
            TimeItem(time = minutesState.toString(), label = "MINUTOS"),
            TimeItem(time = secondsState.toString(), label = "SEGUNDOS")
        )
        countDownList.forEach { CountDownItem(it) }
    }
}

@Composable
fun CountDownItem(timeItem: TimeItem) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Box(
            modifier = Modifier
                .size(50.dp)
                .background(Color.LightGray)
                .clip(CircleShape),
            contentAlignment = Alignment.Center
        ) {
            Text(text = timeItem.time)
        }
        Text(
            text = timeItem.label,
            fontSize = 10.sp
        )
    }
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