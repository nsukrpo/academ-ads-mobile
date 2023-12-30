package nsu.krpo.academads.ui.purchases.rv

import android.graphics.drawable.Drawable
import nsu.krpo.academads.domain.model.ads.Purchase

data class PurchaseWrapper (
    val ad: Purchase,
    val cover: Drawable
)