package nsu.krpo.academads.domain.model.ads

import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Drawable
import java.io.Serializable

data class AdvertisementPhoto(
    val photo: Drawable
) : Serializable
