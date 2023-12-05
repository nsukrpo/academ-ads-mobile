package nsu.krpo.academads.ui.profile.ads_rv

import android.graphics.drawable.Drawable
import nsu.krpo.academads.domain.model.ads.AdvertisementStatus

data class AdvertismentWrapper (
    val cover: Drawable,
    val title: String,
    val status: AdvertisementStatus,
)