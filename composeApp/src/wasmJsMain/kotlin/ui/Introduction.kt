package ui

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.widthIn
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.jetbrains.compose.resources.ExperimentalResourceApi
import utils.FontUtils
import utils.INVITATION_DESCRIPTION
import utils.INVITATION_TITLE
import weddinginvitation.composeapp.generated.resources.Res
import weddinginvitation.composeapp.generated.resources.josephsophia

@OptIn(ExperimentalResourceApi::class)
@Composable
fun Introduction(modifier: Modifier) {
    Spacer(modifier = Modifier.height(45.dp))
    Text(
        text = INVITATION_TITLE,
        modifier = modifier,
        fontSize = 44.sp,
        fontFamily = FontUtils.setFontFamily(Res.font.josephsophia),
    )
    Text(
        text = INVITATION_DESCRIPTION,
        modifier = modifier.padding(vertical = 10.dp, horizontal = 25.dp).widthIn(0.dp, 600.dp),
        textAlign = TextAlign.Center,
        fontSize = 16.sp,
        fontFamily = FontFamily.Default
    )
}