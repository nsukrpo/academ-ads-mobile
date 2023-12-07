package nsu.krpo.academads.ui.profile.likes_rv

import android.graphics.drawable.Drawable
import java.math.BigDecimal

data class LikedWrapper (
    val title: String,
    val price: BigDecimal,
    val cover: Drawable,
)