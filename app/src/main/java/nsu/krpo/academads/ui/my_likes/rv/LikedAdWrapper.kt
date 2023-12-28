package nsu.krpo.academads.ui.my_likes.rv

import android.graphics.drawable.Drawable
import nsu.krpo.academads.domain.model.ads.Advertisement

data class LikedAdWrapper (
    val ad: Advertisement,
    val cover: Drawable,
)