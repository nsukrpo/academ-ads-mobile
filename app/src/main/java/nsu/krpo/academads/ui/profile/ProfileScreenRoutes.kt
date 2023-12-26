package nsu.krpo.academads.ui.profile

import nsu.krpo.academads.domain.model.ads.Advertisement

sealed interface ProfileScreenRoutes {

    class ToMyAd(val ad: Advertisement): ProfileScreenRoutes

    class ToAd(val ad: Advertisement): ProfileScreenRoutes

    class ToMyAds(): ProfileScreenRoutes

    class ToMyPurchases(): ProfileScreenRoutes

    class ToLikes(): ProfileScreenRoutes

    class ToCreateAd(): ProfileScreenRoutes

    class ToBans(): ProfileScreenRoutes

    class ToLogIn(): ProfileScreenRoutes
}