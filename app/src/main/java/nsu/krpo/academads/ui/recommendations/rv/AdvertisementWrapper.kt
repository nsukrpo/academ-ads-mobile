package nsu.krpo.academads.ui.recommendations.rv

import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Drawable
import nsu.krpo.academads.domain.model.ads.Advertisement

data class AdvertisementWrapper (
    val ad: Advertisement,
    val cover: Drawable
)