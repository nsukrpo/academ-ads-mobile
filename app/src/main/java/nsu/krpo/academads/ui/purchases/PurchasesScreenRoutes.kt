package nsu.krpo.academads.ui.purchases

import nsu.krpo.academads.domain.model.ads.Advertisement

sealed class PurchasesScreenRoutes {

    class ToAd(val ad: Advertisement): PurchasesScreenRoutes()
}