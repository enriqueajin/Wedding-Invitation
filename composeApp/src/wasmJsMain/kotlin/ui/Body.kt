package ui

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun Body(modifier: Modifier) {
    Introduction(modifier)
    CountDown(modifier)
    Ceremony(modifier)
    DressCode(modifier)
    PhotoGallery(modifier)
    Gifts(modifier)
    AttendanceForm(modifier)
}