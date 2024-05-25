package ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.browser.window
import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.jetbrains.compose.resources.painterResource
import utils.CONT_DESC_ARROW_DOWN
import utils.CONT_DESC_HEADER_IMG_BACKGROUND
import utils.FontUtils.setFontFamily
import utils.HEADER_SPOUSES_NAMES
import utils.HEADER_TITLE
import weddinginvitation.composeapp.generated.resources.Res
import weddinginvitation.composeapp.generated.resources.arrow_down
import weddinginvitation.composeapp.generated.resources.header
import weddinginvitation.composeapp.generated.resources.josephsophia

@OptIn(ExperimentalResourceApi::class)
@Composable
fun Header(modifier: Modifier) {
    Box(
        modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.TopCenter
    ) {
        // Choose background image height based on width pixels.
        val bgImageHeight = when(window.innerWidth) {
            in 550..1023 -> 500
            in 1024..Int.MAX_VALUE -> 550
            else -> 400
        }
        // Change the top padding depending on the window size
        val topTextPadding = when(window.innerWidth) {
            in 1024..Int.MAX_VALUE -> 30
            else -> 50
        }
        Image(
            painter = painterResource(Res.drawable.header),
            contentDescription = CONT_DESC_HEADER_IMG_BACKGROUND,
            modifier = Modifier.height(bgImageHeight.dp),
            contentScale = ContentScale.Crop
        )
        Column(
            modifier = modifier.fillMaxWidth().padding(top = topTextPadding.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = HEADER_TITLE,
                color = Color.White,
                fontSize = 54.sp,
                fontFamily = setFontFamily(Res.font.josephsophia)
            )
            Text(
                text = HEADER_SPOUSES_NAMES,
                color = Color.White,
                fontSize = 20.sp,
                fontFamily = setFontFamily(Res.font.josephsophia)
            )
        }
    }
    Spacer(modifier = Modifier.height(30.dp))
    Icon(
        painter = painterResource(Res.drawable.arrow_down),
        contentDescription = CONT_DESC_ARROW_DOWN,
        modifier = modifier.size(30.dp)
    )
}