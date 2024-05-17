package utils

import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.flow
import kotlinx.datetime.*
import models.TimeUnits

class CustomCountDownTimer(weddingDate: LocalDateTime) {
    val timeUnits = flow {
        // Get wedding date's milliseconds
        val weddingDateMillis = weddingDate.toInstant(TimeZone.of("UTC-6")).toEpochMilliseconds()

        // Get current time and convert into LocalDateTime with UTC-6 time zone
        val localDateNow = Clock.System.now().toLocalDateTime(TimeZone.of("UTC-6"))

        // Convert LocalDateTime into Instant and then, into Long to get the milliseconds
        val startTime = localDateNow.toInstant(TimeZone.of("UTC-6")).toEpochMilliseconds()

        var timeLeft = weddingDateMillis - startTime

        while (timeLeft > 0) {
            val currentLocalDateTime = Clock.System.now().toLocalDateTime(TimeZone.of("UTC-6"))
            val currentMilliseconds = currentLocalDateTime.toInstant(TimeZone.of("UTC-6")).toEpochMilliseconds()

            val seconds = (timeLeft / 1000) % 60
            val minutes = (timeLeft / (1000 * 60)) % 60
            val hours = (timeLeft / (1000 * 60 * 60)) % 24
            val days = timeLeft / (1000 * 60 * 60 * 24)

            val timeUnits = TimeUnits(days, hours, minutes, seconds)
            delay(1000)
            timeLeft = weddingDateMillis - currentMilliseconds
            emit(timeUnits)
        }
    }
}