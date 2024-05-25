package ui

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.PressInteraction
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
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
import models.AttendanceModel
import org.jetbrains.compose.resources.ExperimentalResourceApi
import theme.OliveGreen
import ui.components.WeddingButon
import utils.*
import weddinginvitation.composeapp.generated.resources.Res
import weddinginvitation.composeapp.generated.resources.nexaheavy

@OptIn(ExperimentalResourceApi::class)
@Composable
fun AttendanceForm(modifier: Modifier) {
    // Get params coming in the URL
    val url = window.location.search
    val params = parseQueryString(url, startIndex = 1) // Starting from 0 to remove "?"

    // Get the number of guests allowed
    val guests = if (!params.isEmpty()) params["guests"]?.toInt() else 0

    // Get the DropDown Menu's option list
    val dropDownList = getDropDownList(guests.toString())

    // Manage the attendance form inputs and RadioButtons
    var selected by rememberSaveable { mutableStateOf(ATTENDANCE_YES) }
    var guestName by rememberSaveable { mutableStateOf("") }
    var attendeesQuantity by rememberSaveable { mutableStateOf(guests.toString()) }
    var guestMessage by rememberSaveable { mutableStateOf("") }

    // Handle the request status code
    var statusCode by rememberSaveable { mutableIntStateOf(404) }

    var showFormResponseDialog by rememberSaveable { mutableStateOf(false) }
    var showAttendeesDialog by rememberSaveable { mutableStateOf(false) }

    Column(modifier.widthIn(200.dp, 700.dp)) {
        Spacer(Modifier.height(70.dp))
        Text(
            text = ATTENDANCE,
            fontSize = 25.sp,
            textAlign = TextAlign.Center,
            lineHeight = 35.sp,
            modifier = modifier.padding(horizontal = 50.dp),
            fontFamily = FontUtils.setFontFamily(Res.font.nexaheavy)
        )
        Text(
            text = ATTENDANCE_DESCRIPTION,
            modifier = modifier.padding(vertical = 20.dp, horizontal = 45.dp),
            fontSize = 16.sp,
            fontFamily = FontFamily.Default,
            textAlign = TextAlign.Center
        )
        Divider(
            color = Color.LightGray,
            modifier = modifier.height(1.dp).widthIn(0.dp, 60.dp)
        )
        Text(
            text = "$ATTENDANCE_GUESTS_NUMBER_VALID_FOR $guests $ATTENDANCE_GUESTS_NUMBER_GUESTS",
            fontSize = 18.sp,
            modifier = modifier.padding(vertical = 20.dp),
            fontFamily = FontUtils.setFontFamily(Res.font.nexaheavy)
        )
        Divider(
            color = Color.LightGray,
            modifier = modifier.height(1.dp).widthIn(0.dp, 60.dp)
        )
        Row(
            modifier = modifier.fillMaxWidth().padding(vertical = 35.dp),
            horizontalArrangement = Arrangement.SpaceEvenly,
        ) {
            AttendanceRadioButtonList(selected) { currentValue ->
                selected = currentValue
            }
        }
        OutlinedTextField(
            value = guestName,
            onValueChange = { guestName = it },
            label = { Text(text = ATTENDANCE_FIELD_NAME) },
            modifier = modifier.fillMaxWidth().padding(horizontal = 20.dp, vertical = 5.dp),
            colors = TextFieldDefaults.outlinedTextFieldColors(
                focusedBorderColor = OliveGreen,
                focusedLabelColor = OliveGreen
            ),
        )
        OutlinedTextField(
            value = attendeesQuantity,
            onValueChange = { attendeesQuantity = it },
            readOnly = true,
            label = { Text(text = ATTENDANCE_FIELD_AMOUNT_ATTENDANCES) },
            modifier = Modifier.fillMaxWidth().padding(horizontal = 20.dp, vertical = 5.dp),
            trailingIcon = { Icon(imageVector = Icons.Filled.ArrowDropDown, CONT_DESC_ARROW_DOWN) },
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
                focusedBorderColor = OliveGreen,
                focusedLabelColor = OliveGreen
            ),
        )
        OutlinedTextField(
            value = guestMessage,
            onValueChange = { guestMessage = it },
            label = { Text(text = ATTENDANCE_FIELD_MESSAGE) },
            modifier = modifier.fillMaxWidth().padding(horizontal = 20.dp, vertical = 5.dp),
            minLines = 5,
            maxLines = 5,
            colors = TextFieldDefaults.outlinedTextFieldColors(
                focusedBorderColor = OliveGreen,
                focusedLabelColor = OliveGreen
            ),
        )
        AttendeesQuantityDialog(
            show = showAttendeesDialog,
            attendeesAllowed = dropDownList,
            onDismiss = { showAttendeesDialog = false },
            onQuantityChanged = { newQuantity -> attendeesQuantity = newQuantity }
        )
        Spacer(Modifier.height(10.dp))
        WeddingButon(
            text = ATTENDANCE_BUTTON_TEXT,
            enabled = isFormValid(guestName, attendeesQuantity),
            modifier = modifier,
            onClick = {
                val attendanceModel = AttendanceModel(
                    isAttending = selected == ATTENDANCE_YES,
                    name = guestName,
                    attendeesQuantity = attendeesQuantity.toInt(),
                    message = guestMessage,
                )
                submitForm(
                    attendance = attendanceModel,
                    onStatusCodeReceived = { status -> statusCode = status }
                )
                guestName = ""
                guestMessage = ""
                showFormResponseDialog = true
            },
        )
        FormDialog(
            status = statusCode,
            show = showFormResponseDialog,
            onDismiss = { showFormResponseDialog = false },
        )
    }
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

fun isFormValid(name: String, attendeesQuantity: String): Boolean =
    name.isNotBlank() && attendeesQuantity.toInt() != 0 && attendeesQuantity.matches(Regex("^\\d+\$"))

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
                modifier = Modifier.widthIn(250.dp, 500.dp).height(500.dp).background(Color.White).padding(10.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                if (status == 201) {
                    Text(FORM_DIALOG_SUCCESS)

                } else {
                    Text(FORM_DIALOG_ERROR)
                }
                Button(onClick = { onDismiss() }) {
                    Text(FORM_DIALOG_BUTTON)
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
                    .widthIn(0.dp, 500.dp)
                    .background(Color.White)
                    .padding(10.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Text(
                    text = ATTENDANCE_DIALOG_QUANTITY,
                    modifier = Modifier.align(Alignment.Start).padding(vertical = 10.dp),
                    textAlign = TextAlign.Start
                )
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
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onItemClicked(number) },
        horizontalAlignment = Alignment.Start
    ) {
        Text(text = number, modifier = Modifier.padding(vertical = 5.dp, horizontal = 10.dp))
        Divider(modifier = Modifier.fillMaxWidth().height(1.dp))
    }
}

@Composable
fun AttendanceRadioButtonList(name: String, onItemClick: (String) -> Unit) {
    val interactionSource = remember { MutableInteractionSource() }
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.clickable(
            interactionSource = interactionSource,
            indication = null // Remove ripple effect
        ) { onItemClick(ATTENDANCE_YES) }
    ) {
        val radioButtonText = ATTENDANCE_YES
        RadioButton(
            selected = name == radioButtonText,
            onClick = { onItemClick(radioButtonText) },
            modifier = Modifier.size(20.dp),
            colors = RadioButtonDefaults.colors(selectedColor = OliveGreen)
        )
        Spacer(modifier = Modifier.width(5.dp))
        Text(text = radioButtonText)
    }
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.clickable(
            indication = null,
            interactionSource = interactionSource
        ) { onItemClick(ATTENDANCE_NO) }
    ) {
        val radioButtonText = ATTENDANCE_NO
        RadioButton(
            selected = name == radioButtonText,
            onClick = { onItemClick(radioButtonText) },
            modifier = Modifier.size(20.dp),
            colors = RadioButtonDefaults.colors(selectedColor = OliveGreen)
        )
        Spacer(modifier = Modifier.width(5.dp))
        Text(text = radioButtonText)
    }
}