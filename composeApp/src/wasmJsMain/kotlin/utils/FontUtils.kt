package utils

import androidx.compose.runtime.Composable
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.jetbrains.compose.resources.Font
import org.jetbrains.compose.resources.FontResource

object FontUtils {
    @OptIn(ExperimentalResourceApi::class)
    @Composable
    fun setFontFamily(font: FontResource): FontFamily {
        return FontFamily(
            Font(
                resource = font,
                weight = FontWeight.Normal,
                style = FontStyle.Normal,
            )
        )
    }
}
