package nsu.krpo.academads.ui.my_ads.rv

import android.graphics.drawable.BitmapDrawable
import nsu.krpo.academads.domain.model.ads.Advertisement

data class AdWrapper (
    val ad: Advertisement,
    val cover: BitmapDrawable
)