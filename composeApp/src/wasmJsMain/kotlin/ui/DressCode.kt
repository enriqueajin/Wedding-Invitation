package ui

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.jetbrains.compose.resources.painterResource
import utils.CONT_DESC_DRESS_CODE
import utils.DRESS_CODE
import utils.DRESS_CODE_DESCRIPTION
import utils.FontUtils
import weddinginvitation.composeapp.generated.resources.Res
import weddinginvitation.composeapp.generated.resources.dresscode
import weddinginvitation.composeapp.generated.resources.nexaheavy

@OptIn(ExperimentalResourceApi::class)
@Composable
fun DressCode(modifier: Modifier) {
    Spacer(Modifier.height(60.dp))
    Icon(
        painter = painterResource(Res.drawable.dresscode),
        contentDescription = CONT_DESC_DRESS_CODE,
        modifier = modifier.size(65.dp)
    )
    Spacer(Modifier.height(20.dp))
    Text(
        text = DRESS_CODE,
        fontSize = 25.sp,
        modifier = modifier,
        fontFamily = FontUtils.setFontFamily(Res.font.nexaheavy)
    )
    Text(
        text = DRESS_CODE_DESCRIPTION,
        modifier = modifier.padding(10.dp),
        textAlign = TextAlign.Center,
        lineHeight = 30.sp,
        fontSize = 16.sp,
        fontFamily = FontFamily.Default
    )
}