package ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import kotlinx.browser.window
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.jetbrains.compose.resources.painterResource
import utils.CONT_DESC_IMAGE_SPOUSES
import weddinginvitation.composeapp.generated.resources.*

@OptIn(ExperimentalResourceApi::class)
@Composable
fun PhotoGallery(modifier: Modifier) {
    val smallPhotoHeight = when (window.innerWidth) {
        in 550..1023 -> 400
        in 1024..Int.MAX_VALUE -> 450
        else -> 300
    }
    val bigPhotoHeight = smallPhotoHeight + 100

    Column(modifier = modifier.widthIn(0.dp, 1000.dp)) {
        Spacer(Modifier.height(45.dp))
        GalleryRow(
            modifier = modifier,
            photo1 = Res.drawable.photo1,
            photo2 = Res.drawable.photo4,
            imagesHeight = smallPhotoHeight
        )
        Spacer(Modifier.height(4.dp))
        Image(
            painter = painterResource(Res.drawable.mainphoto),
            contentDescription = CONT_DESC_IMAGE_SPOUSES,
            modifier = modifier
                .padding(horizontal = 20.dp)
                .widthIn(0.dp, 1500.dp)
                .clip(RoundedCornerShape(10.dp))
                .height(bigPhotoHeight.dp),
            contentScale = ContentScale.Crop
        )
        Spacer(Modifier.height(4.dp))
        GalleryRow(
            modifier = modifier,
            photo1 = Res.drawable.photo3,
            photo2 = Res.drawable.photo2,
            imagesHeight = smallPhotoHeight
        )
    }
}

@OptIn(ExperimentalResourceApi::class)
@Composable
fun GalleryRow(
    modifier: Modifier,
    photo1: DrawableResource,
    photo2: DrawableResource,
    imagesHeight: Int
) {
    Row(modifier = modifier.padding(horizontal = 20.dp).widthIn(0.dp, 1500.dp)) {
        Image(
            painter = painterResource(photo1),
            contentDescription = CONT_DESC_IMAGE_SPOUSES,
            modifier = modifier.weight(1f).clip(RoundedCornerShape(10.dp)).height(imagesHeight.dp),
            contentScale = ContentScale.Crop
        )
        Spacer(Modifier.width(4.dp))
        Image(
            painter = painterResource(photo2),
            contentDescription = CONT_DESC_IMAGE_SPOUSES,
            modifier = modifier.weight(1f).clip(RoundedCornerShape(10.dp)).height(imagesHeight.dp),
            contentScale = ContentScale.Crop
        )
    }
}