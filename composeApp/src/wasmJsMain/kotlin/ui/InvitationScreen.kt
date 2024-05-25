package ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier

@Composable
fun Invitation() {
    Column(modifier = Modifier.fillMaxSize().verticalScroll(rememberScrollState())) {
    val modifier = Modifier.align(Alignment.CenterHorizontally)
        Header(modifier)
        Body(modifier)
        Footer(modifier)
    }
}