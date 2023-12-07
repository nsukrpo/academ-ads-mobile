package nsu.krpo.academads.ui.profile.purchases_rv

import android.graphics.drawable.Drawable
import nsu.krpo.academads.domain.model.ads.Purchase

data class PurchaseWrapper(
    val purchase: Purchase,
    val cover: Drawable,
)
