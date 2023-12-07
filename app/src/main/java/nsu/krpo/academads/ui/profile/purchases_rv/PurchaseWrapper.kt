package nsu.krpo.academads.ui.profile.purchases_rv

import android.graphics.drawable.Drawable
import nsu.krpo.academads.domain.model.ads.Advertisement
import nsu.krpo.academads.domain.model.ads.User
import java.math.BigDecimal
import java.util.Date

data class PurchaseWrapper(
    val ad: Advertisement,
    val price: BigDecimal,
    val date: Date,
    val buyer: User?,
    val cover: Drawable,
)
