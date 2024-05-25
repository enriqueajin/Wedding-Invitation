package ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.datetime.LocalDateTime
import models.TimeItem
import models.TimeUnits
import theme.Yellow
import utils.*

@Composable
fun CountDown(modifier: Modifier) {
    // Wedding's date: 08/25/2024 at 13:30
    val weddingDateTime = LocalDateTime(
        year = 2024,
        monthNumber = 8,
        dayOfMonth = 25,
        hour = 13,
        minute = 30,
        second = 0
    )
    val customCountDown = CustomCountDownTimer(weddingDateTime)
    val timeUnits = customCountDown.timeUnits.collectAsState(TimeUnits())

    Spacer(modifier = Modifier.height(30.dp))
    Row(
        modifier = modifier.widthIn(700.dp, 1000.dp).padding(horizontal = 25.dp),
        horizontalArrangement = Arrangement.SpaceAround
    ) {
        val countDownList = listOf(
            TimeItem(time = timeUnits.value.days.toString(), label = DAYS),
            TimeItem(time = timeUnits.value.hours.toString(), label = HOURS),
            TimeItem(time = timeUnits.value.minutes.toString(), label = MINUTES),
            TimeItem(time = timeUnits.value.seconds.toString(), label = SECONDS)
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
                .background(Yellow),
            contentAlignment = Alignment.Center
        ) {
            Text(text = timeItem.time)
        }
        Text(text = timeItem.label, fontSize = 10.sp)
    }
}