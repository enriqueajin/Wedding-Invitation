package models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class AttendanceModel(
    @SerialName("is_attending")
    val isAttending: Boolean,

    @SerialName("name")
    val name: String,

    @SerialName("attendees_quantity")
    val attendeesQuantity: Int,

    @SerialName("message")
    val message: String = ""
)