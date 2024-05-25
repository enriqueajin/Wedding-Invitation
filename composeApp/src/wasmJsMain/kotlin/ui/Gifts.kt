package ui

import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.jetbrains.compose.resources.painterResource
import ui.components.WeddingButon
import utils.*
import weddinginvitation.composeapp.generated.resources.Res
import weddinginvitation.composeapp.generated.resources.gift
import weddinginvitation.composeapp.generated.resources.nexaheavy

@OptIn(ExperimentalResourceApi::class)
@Composable
fun Gifts(modifier: Modifier) {
    val uriHandler = LocalUriHandler.current

    Spacer(Modifier.height(65.dp))
    Icon(
        painter = painterResource(Res.drawable.gift),
        contentDescription = CONT_DESC_GIFT_ICON,
        modifier = modifier.size(65.dp)
    )
    Spacer(Modifier.height(20.dp))
    Text(
        text = GIFTS,
        fontSize = 25.sp,
        modifier = modifier,
        fontFamily = FontUtils.setFontFamily(Res.font.nexaheavy)
    )
    Text(
        text = GIFTS_DESCRIPTION,
        modifier = modifier.padding(vertical = 15.dp, horizontal = 45.dp).widthIn(0.dp, 500.dp),
        fontSize = 16.sp,
        fontFamily = FontFamily.Default,
        textAlign = TextAlign.Center
    )
    Spacer(Modifier.height(5.dp))
    WeddingButon(
        text = GIFTS_BUTTON_TEXT,
        modifier = modifier,
        onClick = { uriHandler.openUri("https://www.cemaco.com/list/bodaajinguillen") }
    )
}