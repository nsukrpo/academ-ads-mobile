package nsu.krpo.academads.ui.profile.likes_rv

import android.graphics.drawable.Drawable
import nsu.krpo.academads.domain.model.ads.Advertisement

data class LikedWrapper (
    val ad: Advertisement,
    val cover: Drawable,
)