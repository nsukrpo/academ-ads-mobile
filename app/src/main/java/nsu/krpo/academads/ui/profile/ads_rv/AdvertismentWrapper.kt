package nsu.krpo.academads.ui.profile.ads_rv

import android.graphics.drawable.Drawable
import nsu.krpo.academads.domain.model.ads.Advertisement

data class AdvertismentWrapper (
    val ad: Advertisement,
    val cover: Drawable,
)