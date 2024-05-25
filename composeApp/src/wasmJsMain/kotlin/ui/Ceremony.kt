package ui

import androidx.compose.foundation.layout.*
import androidx.compose.material.Divider
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.jetbrains.compose.resources.painterResource
import theme.LightGray
import ui.components.WeddingButon
import utils.*
import weddinginvitation.composeapp.generated.resources.Res
import weddinginvitation.composeapp.generated.resources.nexaextralight
import weddinginvitation.composeapp.generated.resources.nexaheavy
import weddinginvitation.composeapp.generated.resources.rings

@OptIn(ExperimentalResourceApi::class)
@Composable
fun Ceremony(modifier: Modifier) {
    val uriHandler = LocalUriHandler.current

    Spacer(Modifier.height(60.dp))
    Icon(
        painter = painterResource(Res.drawable.rings),
        contentDescription = CONT_DESC_RINGS,
        modifier = modifier.size(65.dp)
    )
    Spacer(Modifier.height(20.dp))
    Text(
        text = CEREMONY,
        fontSize = 25.sp,
        modifier = modifier,
        fontFamily = FontUtils.setFontFamily(Res.font.nexaheavy)
    )
    Spacer(Modifier.height(5.dp))
    Text(
        text = CEREMONY_PLACE,
        modifier = modifier.padding(10.dp),
        textAlign = TextAlign.Center,
        lineHeight = 30.sp,
        fontSize = 16.sp,
        fontFamily = FontFamily.Default
    )
    Spacer(Modifier.height(25.dp))
    Row(
        modifier = modifier.fillMaxHeight(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = CEREMONY_DATE,
            modifier = modifier.padding(20.dp),
            textAlign = TextAlign.Center,
            fontSize = 25.sp,
            fontFamily = FontUtils.setFontFamily(Res.font.nexaextralight)
        )
        Divider(
            color = LightGray,
            modifier = modifier.width(2.dp).height(80.dp),
        )
        Row(modifier = modifier.padding(20.dp)) {
            Text(
                text = CEREMONY_TIME,
                modifier = modifier,
                textAlign = TextAlign.Center,
                fontSize = 25.sp,
                fontFamily = FontUtils.setFontFamily(Res.font.nexaextralight)
            )
            Spacer(Modifier.size(5.dp))
            Text(
                text = CEREMONY_TIME_SUFFIX,
                textAlign = TextAlign.Center,
                modifier = modifier,
                fontSize = 14.sp,
                fontFamily = FontUtils.setFontFamily(Res.font.nexaextralight)
            )
        }
    }
    Text(
        text = CEREMONY_ADDRESS,
        modifier = modifier.padding(vertical = 15.dp, horizontal = 25.dp),
        textAlign = TextAlign.Center,
        fontSize = 16.sp,
        fontFamily = FontFamily.Default
    )
    Spacer(Modifier.height(10.dp))
    WeddingButon(
        text = CEREMONY_BUTTON_LOCATION,
        modifier = modifier,
        onClick = { uriHandler.openUri("https://maps.app.goo.gl/RKfQ4BGUi8fUBgT37") }
    )
}