package screens

import utils.CustomCountDownTimer
import utils.CustomHttpClient
import androidx.compose.foundation.*
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.PressInteraction
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
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
import androidx.compose.ui.window.Dialog
import io.ktor.client.request.*
import io.ktor.http.*
import kotlinx.browser.window
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.datetime.*
import models.AttendanceModel
import models.TimeItem
import models.TimeUnits
import org.jetbrains.compose.resources.*
import utils.Constants
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
        Footer(modifier = Modifier.align(Alignment.CenterHorizontally))
    }
}

@OptIn(ExperimentalResourceApi::class)
@Composable
fun Header() {
    Box(
        modifier = Modifier.fillMaxWidth(),
        contentAlignment = Alignment.TopCenter
    ) {
        Image(
            painter = painterResource(Res.drawable.header),
            contentDescription = Constants.CONT_DESC_HEADER_IMG_BACKGROUND,
            modifier = Modifier.height(500.dp),
            contentScale = ContentScale.Crop
        )
        Column(
            modifier = Modifier.fillMaxWidth().height(300.dp),
            verticalArrangement = Arrangement.SpaceAround,
        ) {
            Spacer(Modifier.height(20.dp))
            Column(
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = Constants.HEADER_TITLE,
                    color = Color.White,
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
                    color = Color.White,
                    fontSize = 20.sp,
                    fontFamily = FontFamily(
                        Font(
                            resource = Res.font.josephsophia,
                            weight = FontWeight.Bold,
                            style = FontStyle.Normal,
                        )
                    ),
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
            Spacer(modifier = Modifier.height(100.dp))
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
        eventAddress =
        Constants.CELEBRATION_ADDRESS,
        buttonText = Constants.CEREMONY_BUTTON_LOCATION
    )
    DressCode(modifier)
    PhotoGallery(modifier)
    Gifts(modifier)
    Attendance(modifier)
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
        modifier = modifier.padding(vertical = 10.dp, horizontal = 25.dp),
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
        modifier = modifier.fillMaxWidth().padding(horizontal = 25.dp),
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
                .background(Color(0xFFfcd49f)),
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
        modifier = modifier.padding(vertical = 15.dp, horizontal = 25.dp),
        textAlign = TextAlign.Center,
        fontSize = 16.sp,
        fontFamily = FontFamily.Default
    )
    Button(
        onClick = {},
        modifier = modifier.padding(12.dp),
        colors = ButtonDefaults.buttonColors(
            backgroundColor = Color(0xFFACBA90),
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
        contentDescription = Constants.CONT_DESC_DRESS_CODE,
        modifier = modifier.size(65.dp)
    )
    Spacer(Modifier.height(20.dp))
    Text(
        text = Constants.DRESS_CODE,
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
        text = Constants.DRESS_CODE_DESCRIPTION,
        modifier = modifier.padding(10.dp),
        textAlign = TextAlign.Center,
        lineHeight = 30.sp,
        fontSize = 16.sp,
        fontFamily = FontFamily.Default
    )
}

@OptIn(ExperimentalResourceApi::class)
@Composable
fun PhotoGallery(modifier: Modifier) {
    Spacer(Modifier.height(45.dp))
    GalleryRow(
        modifier = modifier,
        photo1 = Res.drawable.photo1,
        photo2 = Res.drawable.photo4
    )
    Spacer(Modifier.height(4.dp))
    Image(
        painter = painterResource(Res.drawable.mainphoto),
        contentDescription = Constants.CONT_DESC_IMAGE_SPOUSES,
        modifier = modifier.padding(horizontal = 20.dp)
            .widthIn(0.dp, 1500.dp)
            .clip(RoundedCornerShape(10.dp))
            .height(200.dp),
        contentScale = ContentScale.Crop
    )
    Spacer(Modifier.height(4.dp))
    GalleryRow(
        modifier = modifier,
        photo1 = Res.drawable.photo3,
        photo2 = Res.drawable.photo2
    )
}

@OptIn(ExperimentalResourceApi::class)
@Composable
fun GalleryRow(modifier: Modifier, photo1: DrawableResource, photo2: DrawableResource) {
    Row(
        modifier = modifier
            .padding(horizontal = 20.dp)
            .widthIn(0.dp, 1500.dp)
    ) {
        Image(
            painter = painterResource(photo1),
            contentDescription = Constants.CONT_DESC_IMAGE_SPOUSES,
            modifier = modifier
                .weight(1f)
                .clip(RoundedCornerShape(10.dp))
                .height(200.dp),
            contentScale = ContentScale.Crop
        )
        Spacer(Modifier.width(4.dp))
        Image(
            painter = painterResource(photo2),
            contentDescription = Constants.CONT_DESC_IMAGE_SPOUSES,
            modifier = modifier
                .weight(1f)
                .clip(RoundedCornerShape(10.dp))
                .height(200.dp),
            contentScale = ContentScale.Crop
        )
    }
}

@OptIn(ExperimentalResourceApi::class)
@Composable
fun Gifts(modifier: Modifier) {
    Spacer(Modifier.height(65.dp))
    Icon(
        painter = painterResource(Res.drawable.gift),
        contentDescription = Constants.CONT_DESC_GIFT_ICON,
        modifier = modifier.size(65.dp)
    )
    Spacer(Modifier.height(20.dp))
    Text(
        text = Constants.GIFTS,
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
        text = Constants.GIFTS_DESCRIPTION,
        modifier = modifier.padding(vertical = 15.dp, horizontal = 45.dp),
        fontSize = 16.sp,
        fontFamily = FontFamily.Default,
        textAlign = TextAlign.Center
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
            text = Constants.GIFTS_BUTTON_TEXT,
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
fun Attendance(modifier: Modifier) {
    Spacer(Modifier.height(70.dp))
    Text(
        text = Constants.ATTENDANCE,
        fontSize = 25.sp,
        textAlign = TextAlign.Center,
        lineHeight = 35.sp,
        modifier = modifier.padding(horizontal = 50.dp),
        fontFamily = FontFamily(
            Font(
                resource = Res.font.nexaheavy,
                weight = FontWeight.Bold,
                style = FontStyle.Normal,
            )
        ),
    )
    Text(
        text = Constants.ATTENDANCE_DESCRIPTION,
        modifier = modifier.padding(vertical = 20.dp, horizontal = 45.dp),
        fontSize = 16.sp,
        fontFamily = FontFamily.Default,
        textAlign = TextAlign.Center
    )
    Divider(
        color = Color.LightGray,
        modifier = modifier
            .fillMaxWidth()
            .height(1.dp)
            .padding(horizontal = 130.dp)
    )
    val url = window.location.search
    val params = parseQueryString(
        url,
        startIndex = 1 // Starting from 0 to remove "?"
    )
    val guests = if (!params.isEmpty()) params["guests"]?.toInt() else 0

    Text(
        text = "${Constants.ATTENDANCE_GUESTS_NUMBER_VALID_FOR} $guests ${Constants.ATTENDANCE_GUESTS_NUMBER_GUESTS}",
        fontSize = 18.sp,
        modifier = modifier.padding(vertical = 20.dp),
        fontFamily = FontFamily(
            Font(
                resource = Res.font.nexaheavy,
                weight = FontWeight.Bold,
                style = FontStyle.Normal,
            )
        ),
    )
    Divider(
        color = Color.LightGray,
        modifier = modifier
            .fillMaxWidth()
            .height(1.dp)
            .padding(horizontal = 130.dp)
    )

    // Manage the attendance form inputs and RadioButtons
    var selected by rememberSaveable { mutableStateOf(Constants.ATTENDANCE_YES) }
    var guestName by rememberSaveable { mutableStateOf("") }
    var attendeesQuantity by rememberSaveable { mutableStateOf(guests.toString()) }
    var guestMessage by rememberSaveable { mutableStateOf("") }


    var statusCode by rememberSaveable { mutableIntStateOf(404) }
    var show by rememberSaveable { mutableStateOf(false) }
    var showAttendeesDialog by rememberSaveable { mutableStateOf(false) }

    // Get the DropDown Menu's option list
    val dropDownList = getDropDownList(guests.toString())
    println(dropDownList)

    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(vertical = 35.dp),
        horizontalArrangement = Arrangement.SpaceEvenly,
    ) {
        AttendanceRadioButtonList(selected) {
            selected = it
        }
    }
    OutlinedTextField(
        value = guestName,
        onValueChange = { guestName = it },
        label = { Text(text = Constants.ATTENDANCE_FIELD_NAME) },
        modifier = modifier.fillMaxWidth().padding(horizontal = 20.dp),
        colors = TextFieldDefaults.outlinedTextFieldColors(
            focusedBorderColor = Color(0xFF408df7),
            focusedLabelColor = Color(0xFF408df7)
        ),
    )
    OutlinedTextField(
        value = attendeesQuantity,
        onValueChange = { attendeesQuantity = it },
        readOnly = true,
        label = { Text(text = Constants.ATTENDANCE_FIELD_AMOUNT_ATTENDANCES) },
        modifier = Modifier.fillMaxWidth().padding(horizontal = 20.dp),
        trailingIcon = { Icon(imageVector = Icons.Filled.ArrowDropDown, "") },
        interactionSource = remember { MutableInteractionSource() }
            // Since normal "clickable" has no effect through modifier, used an interactionSource instead
            .also { interactionSource ->
                LaunchedEffect(interactionSource) {
                    interactionSource.interactions.collect {
                        if (it is PressInteraction.Press) {
                            showAttendeesDialog = true
                        }
                    }
                }
            },
        colors = TextFieldDefaults.outlinedTextFieldColors(
            focusedBorderColor = Color(0xFF408df7),
            focusedLabelColor = Color(0xFF408df7)
        ),
    )
    OutlinedTextField(
        value = guestMessage,
        onValueChange = { guestMessage = it },
        label = { Text(text = Constants.ATTENDANCE_FIELD_MESSAGE) },
        modifier = modifier.fillMaxWidth().padding(horizontal = 20.dp),
        minLines = 5,
        maxLines = 5,
        colors = TextFieldDefaults.outlinedTextFieldColors(
            focusedBorderColor = Color(0xFF408df7),
            focusedLabelColor = Color(0xFF408df7)
        ),
    )
    AttendeesQuantityDialog(
        show = showAttendeesDialog,
        attendeesAllowed = dropDownList,
        onDismiss = { showAttendeesDialog = false },
        onQuantityChanged = { newQuantity -> attendeesQuantity = newQuantity }
    )
    Button(
        onClick = {
            submitForm(
                attendance = AttendanceModel(
                    isAttending = selected == Constants.ATTENDANCE_YES,
                    name = guestName,
                    attendeesQuantity = attendeesQuantity.toInt(),
                    message = guestMessage,
                ), onStatusCodeReceived = { status -> statusCode = status }
            )
            guestName = ""
            guestMessage = ""
            show = true
        },
        modifier = modifier.padding(12.dp),
        colors = ButtonDefaults.buttonColors(
            backgroundColor = Color(0xFF408df7),
            contentColor = Color.White
        ),
        shape = RoundedCornerShape(20),
        enabled = isFormValid(guestName, attendeesQuantity)
    ) {
        Text(
            text = Constants.ATTENDANCE_BUTTON_TEXT,
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
    FormDialog(
        status = statusCode,
        show = show,
        onDismiss = { show = false },
    )

}

fun getDropDownList(attendeesQuantity: String): List<String> {
    var maxAttendees = attendeesQuantity.toInt()
    val intDropDownList = mutableListOf<Int>()

    while (maxAttendees > 0) {
        intDropDownList.add(maxAttendees)
        maxAttendees -= 1
    }
    val stringDropDownList = intDropDownList.toList().map { it.toString() }
    return stringDropDownList.reversed()
}

fun isFormValid(name: String, attendeesQuantity: String): Boolean {
    return name.isNotBlank() && attendeesQuantity.matches(Regex("^\\d+\$"))
}

fun submitForm(attendance: AttendanceModel, onStatusCodeReceived: (Int) -> Unit) {
    val client = CustomHttpClient.getClient()

    CoroutineScope(Dispatchers.Default).launch {
        val response = client.post("http://localhost:8000/attendees") {
            contentType(ContentType.Application.Json)
            setBody(attendance)
        }
        onStatusCodeReceived(response.status.value)
    }
}

@Composable
fun FormDialog(
    status: Int,
    show: Boolean,
    onDismiss: () -> Unit,
) {
    if (show) {
        Dialog(onDismissRequest = { onDismiss() }) {
            Column(
                modifier = Modifier
                    .widthIn(250.dp, 500.dp)
                    .height(500.dp)
                    .background(Color.White)
                    .padding(10.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                if (status == 201) {
                    Text("¡Gracias por tu confirmación!")

                } else {
                    Text("Ha ocurrido un error. Por favor intenta de nuevo.")
                }
                Button(onClick = { onDismiss() }) {
                    Text("Aceptar")
                }
            }
        }
    }
}

@Composable
fun AttendeesQuantityDialog(
    show: Boolean,
    attendeesAllowed: List<String>,
    onDismiss: () -> Unit,
    onQuantityChanged: (String) -> Unit
) {
    if (show) {
        Dialog(onDismissRequest = { onDismiss() }) {
            Column(
                modifier = Modifier
                    .widthIn(250.dp, 500.dp)
                    .height(500.dp)
                    .background(Color.White)
                    .padding(10.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                LazyColumn(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                    items(attendeesAllowed) {
                        SelectAttendeesItem(number = it) { value ->
                            onQuantityChanged(value)
                            onDismiss()
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun SelectAttendeesItem(number: String, onItemClicked: (String) -> Unit) {
    Column(
        modifier = Modifier.fillMaxWidth().clickable { onItemClicked(number) },
        horizontalAlignment = Alignment.Start
    ) {
        Text(text = number, modifier = Modifier.padding(vertical = 5.dp, horizontal = 10.dp))
        Divider(modifier = Modifier.fillMaxWidth().height(1.dp))
    }
}

@Composable
fun AttendanceRadioButtonList(name: String, onItemClick: (String) -> Unit) {
    Row(verticalAlignment = Alignment.CenterVertically) {
        val radioButtonText = Constants.ATTENDANCE_YES
        RadioButton(
            selected = name == radioButtonText,
            onClick = { onItemClick(radioButtonText) },
            modifier = Modifier.size(20.dp),
            colors = RadioButtonDefaults.colors(selectedColor = Color(0xFF408df7))
        )
        Spacer(modifier = Modifier.width(5.dp))
        Text(text = radioButtonText)
    }
    Row(verticalAlignment = Alignment.CenterVertically) {
        val radioButtonText = Constants.ATTENDANCE_NO
        RadioButton(
            selected = name == radioButtonText,
            onClick = { onItemClick(radioButtonText) },
            modifier = Modifier.size(20.dp),
            colors = RadioButtonDefaults.colors(selectedColor = Color(0xFF408df7))
        )
        Spacer(modifier = Modifier.width(5.dp))
        Text(text = radioButtonText)
    }
}

@OptIn(ExperimentalResourceApi::class)
@Composable
fun Footer(modifier: Modifier) {
    Spacer(Modifier.height(60.dp))
    Text(
        text = Constants.FOOTER_TITLE,
        modifier = modifier,
        fontSize = 58.sp,
        fontFamily = FontFamily(
            Font(
                resource = Res.font.antaradistance,
                weight = FontWeight.Bold,
                style = FontStyle.Normal,
            )
        ),
    )
    Spacer(Modifier.height(5.dp))
    Text(
        text = Constants.FOOTER_SPOUSES_NAMES,
        modifier = modifier,
        fontSize = 50.sp,
        fontFamily = FontFamily(
            Font(
                resource = Res.font.miltononebold,
                weight = FontWeight.Normal,
                style = FontStyle.Normal,
            )
        ),
    )
    Spacer(Modifier.height(85.dp))
}