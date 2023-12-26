package nsu.krpo.academads.ui.category

import nsu.krpo.academads.domain.model.ads.Advertisement

sealed class CategoryScreenRoutes {
    class ToAd(val ad: Advertisement): CategoryScreenRoutes()
}