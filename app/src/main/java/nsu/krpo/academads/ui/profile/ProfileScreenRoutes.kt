package nsu.krpo.academads.ui.profile

import nsu.krpo.academads.domain.model.ads.Advertisement
import nsu.krpo.academads.domain.model.ads.Purchase

sealed interface ProfileScreenRoutes {

    class ToAd(ad: Advertisement): ProfileScreenRoutes

    class ToPurchase(purchase: Purchase): ProfileScreenRoutes

    class ToMyAds(): ProfileScreenRoutes

    class ToMyPurchases(): ProfileScreenRoutes

    class ToLikes(): ProfileScreenRoutes

    class ToCreateAd(): ProfileScreenRoutes

    class ToBans(): ProfileScreenRoutes
}