package ui

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.jetbrains.compose.resources.ExperimentalResourceApi
import utils.FOOTER_SPOUSES_NAMES
import utils.FOOTER_TITLE
import utils.FontUtils
import weddinginvitation.composeapp.generated.resources.Res
import weddinginvitation.composeapp.generated.resources.antaradistance
import weddinginvitation.composeapp.generated.resources.miltononebold

@OptIn(ExperimentalResourceApi::class)
@Composable
fun Footer(modifier: Modifier) {
    Spacer(Modifier.height(60.dp))
    Text(
        text = FOOTER_TITLE,
        modifier = modifier,
        fontSize = 58.sp,
        fontFamily = FontUtils.setFontFamily(Res.font.antaradistance)
    )
    Spacer(Modifier.height(5.dp))
    Text(
        text = FOOTER_SPOUSES_NAMES,
        modifier = modifier,
        fontSize = 50.sp,
        fontFamily = FontUtils.setFontFamily(Res.font.miltononebold)
    )
    Spacer(Modifier.height(85.dp))
}