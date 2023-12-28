package nsu.krpo.academads.ui.my_likes

import nsu.krpo.academads.domain.model.ads.Advertisement

sealed class MyLikesScreenRoutes {
    class ToAd(val ad: Advertisement) : MyLikesScreenRoutes()
}