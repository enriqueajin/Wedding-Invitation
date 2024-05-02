import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.datetime.*

object CustomCountDownTimer {
    fun setTimer(
        weddingDate: LocalDateTime,
        onTick: (days: Long, hours: Long, minutes: Long, seconds: Long) -> Unit,
        onFinish: () -> Unit
    ) {
        val weddingDateMillis = weddingDate.toInstant(TimeZone.of("UTC-6")).toEpochMilliseconds()
        val scope = CoroutineScope(Dispatchers.Default)

        scope.launch {
            var timeLeft = weddingDateMillis - Clock.System.now().toEpochMilliseconds()
            while (timeLeft > 0) {
                val currentMoment = Clock.System.now().toEpochMilliseconds()
                val seconds = (timeLeft / 1000) % 60
                val minutes = (timeLeft / (1000 * 60)) % 60
                val hours = (timeLeft / (1000 * 60 * 60)) % 24
                val days = timeLeft / (1000 * 60 * 60 * 24)

                onTick(days, hours, minutes, seconds)
                delay(1000)
                timeLeft = weddingDateMillis - currentMoment
            }
            onFinish()
        }
    }
}