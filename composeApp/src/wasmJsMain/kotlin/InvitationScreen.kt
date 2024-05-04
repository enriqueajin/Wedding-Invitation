import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.*
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
            contentDescription = Constants.CONT_DESC_HEADER_IMG_BACKGROUND,
            modifier = Modifier.height(300.dp),
            contentScale = ContentScale.Crop
        )
        Column(
            verticalArrangement = Arrangement.SpaceAround,
            modifier = Modifier.height(300.dp)
        ) {
            Column {
                Text(
                    text = Constants.HEADER_TITLE,
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
                    text = Constants.HEADER_SPOUSES_NAMES,
                    fontSize = 18.sp,
                    fontFamily = FontFamily.Default,
                )
            }
            Icon(
                painter = painterResource(Res.drawable.arrow_down),
                contentDescription = Constants.CONT_DESC_ARROW_DOWN,
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
            DressCode()
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
        text = Constants.INVITATION_TITLE,
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
        text = Constants.INVITATION_DESCRIPTION,
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
    val customCountDown = CustomCountDownTimer(weddingDateTime)
    val timeUnits = customCountDown.timeUnits.collectAsState(TimeUnits())

    Spacer(modifier = Modifier.height(20.dp))
    Row(
        modifier = modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceAround
    ) {
        val countDownList = listOf(
            TimeItem(
                time = timeUnits.value.days.toString(),
                label = Constants.DAYS
            ),
            TimeItem(
                time = timeUnits.value.hours.toString(),
                label = Constants.HOURS
            ),
            TimeItem(
                time = timeUnits.value.minutes.toString(),
                label = Constants.MINUTES
            ),
            TimeItem(
                time = timeUnits.value.seconds.toString(),
                label = Constants.SECONDS
            )
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
                .clip(CircleShape)
                .background(Color(0xFFBECBDB)),
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
fun DressCode() {
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