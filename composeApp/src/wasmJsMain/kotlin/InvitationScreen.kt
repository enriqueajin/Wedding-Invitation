import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
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
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
    ) {
        Header()
        Body(modifier = Modifier.align(Alignment.CenterHorizontally))
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

@OptIn(ExperimentalResourceApi::class)
@Composable
fun Body(modifier: Modifier) {
    InvitationText(modifier)
    CountDown(modifier)
    // Ceremony section
    Event(
        modifier = modifier,
        painter = painterResource(Res.drawable.rings),
        title = Constants.CEREMONY,
        eventDescription = Constants.CEREMONY_PLACE,
        eventDate = Constants.CEREMONY_DATE,
        eventTime = Constants.CEREMONY_TIME,
        eventTimeSuffix = Constants.CEREMONY_TIME_SUFFIX,
        eventAddress = Constants.CEREMONY_ADDRESS,
        buttonText = Constants.CEREMONY_BUTTON_LOCATION
    )
    // Celebration section
    Event(
        modifier = modifier,
        painter = painterResource(Res.drawable.champan),
        title = Constants.CELEBRATION,
        eventDescription = Constants.CELEBRATION_PLACE,
        eventDate = Constants.CELEBRATION_DATE,
        eventTime = Constants.CELEBRATION_TIME,
        eventTimeSuffix = Constants.CELEBRATION_TIME_SUFFIX,
        eventAddress = Constants.CELEBRATION_ADDRESS,
        buttonText = Constants.CEREMONY_BUTTON_LOCATION
    )
    DressCode(modifier)
    PhotoGallery()
    Gifts()
    Attendance()
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
        fontSize = 16.sp,
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

@OptIn(ExperimentalResourceApi::class)
@Composable
fun Event(
    modifier: Modifier,
    painter: Painter,
    title: String,
    eventDescription: String,
    eventDate: String,
    eventTime: String,
    eventTimeSuffix: String,
    eventAddress: String,
    buttonText: String
) {
    Spacer(Modifier.height(60.dp))
    Icon(
        painter = painter,
        contentDescription = Constants.CONT_DESC_RINGS,
        modifier = modifier.size(65.dp)
    )
    Spacer(Modifier.height(20.dp))
    Text(
        text = title,
        fontSize = 25.sp,
        modifier = modifier,
        fontFamily = FontFamily(
            Font(
                resource = Res.font.nexaheavy,
                weight = FontWeight.Bold,
                style = FontStyle.Normal,
            )
        ),
    )
    Spacer(Modifier.height(5.dp))
    Text(
        text = eventDescription,
        modifier = modifier.padding(10.dp),
        textAlign = TextAlign.Center,
        lineHeight = 30.sp,
        fontSize = 16.sp,
        fontFamily = FontFamily.Default
    )
    Spacer(Modifier.height(25.dp))
    Row(
        modifier = modifier.fillMaxHeight(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = eventDate,
            modifier = modifier.padding(20.dp),
            textAlign = TextAlign.Center,
            fontSize = 25.sp,
            fontFamily = FontFamily(
                Font(
                    resource = Res.font.nexaextralight,
                    weight = FontWeight.ExtraBold,
                    style = FontStyle.Normal,
                )
            )
        )
        Divider(
            color = Color(0xFFE6E6E6),
            modifier = modifier.width(2.dp).height(80.dp),
        )
        Row(modifier = modifier.padding(20.dp)) {
            Text(
                text = eventTime,
                modifier = modifier,
                textAlign = TextAlign.Center,
                fontSize = 25.sp,
                fontFamily = FontFamily(
                    Font(
                        resource = Res.font.nexaextralight,
                        weight = FontWeight.ExtraBold,
                        style = FontStyle.Normal,
                    )
                )
            )
            Spacer(Modifier.size(5.dp))
            Text(
                text = eventTimeSuffix,
                textAlign = TextAlign.Center,
                modifier = modifier,
                fontSize = 14.sp,
                fontFamily = FontFamily(
                    Font(
                        resource = Res.font.nexaextralight,
                        weight = FontWeight.Normal,
                        style = FontStyle.Normal,
                    )
                )
            )
        }
    }
    Text(
        text = eventAddress,
        modifier = modifier.padding(15.dp),
        textAlign = TextAlign.Center,
        fontSize = 16.sp,
        fontFamily = FontFamily.Default
    )
    Button(
        onClick = {},
        modifier = modifier.padding(12.dp),
        colors = ButtonDefaults.buttonColors(
            backgroundColor = Color(0xFF408df7),
            contentColor = Color.White
        ),
        shape = RoundedCornerShape(20)
    ) {
        Text(
            text = buttonText,
            fontSize = 12.sp,
            modifier = modifier.padding(horizontal = 10.dp, vertical = 5.dp),
            fontFamily = FontFamily(
                Font(
                    resource = Res.font.nexaextralight,
                    weight = FontWeight.Normal,
                    style = FontStyle.Normal,
                )
            )
        )
    }
}

@OptIn(ExperimentalResourceApi::class)
@Composable
fun DressCode(modifier: Modifier) {
    Spacer(Modifier.height(60.dp))
    Icon(
        painter = painterResource(Res.drawable.dresscode),
        contentDescription = Constants.CONT_DESC_DRESSCODE,
        modifier = modifier.size(65.dp)
    )
    Spacer(Modifier.height(20.dp))
    Text(
        text = Constants.DRESSCODE,
        fontSize = 25.sp,
        modifier = modifier,
        fontFamily = FontFamily(
            Font(
                resource = Res.font.nexaheavy,
                weight = FontWeight.Bold,
                style = FontStyle.Normal,
            )
        ),
    )
    Text(
        text = Constants.DRESSCODE_DESCRIPTION,
        modifier = modifier.padding(10.dp),
        textAlign = TextAlign.Center,
        lineHeight = 30.sp,
        fontSize = 16.sp,
        fontFamily = FontFamily.Default
    )
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