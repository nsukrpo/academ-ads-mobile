package nsu.krpo.academads.ui.my_ads.rv

import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Drawable
import nsu.krpo.academads.domain.model.ads.Advertisement

data class AdWrapper (
    val ad: Advertisement,
    val cover: Drawable
)