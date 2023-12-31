package nsu.krpo.academads.ui.category.rv

import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Drawable
import nsu.krpo.academads.domain.model.ads.Advertisement
import java.math.BigDecimal
import java.util.Date

data class AdvertisementWrapper (
    val ad: Advertisement,
    val cover: Drawable
)