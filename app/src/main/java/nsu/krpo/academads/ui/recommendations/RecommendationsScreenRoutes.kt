package nsu.krpo.academads.ui.recommendations

import nsu.krpo.academads.domain.model.ads.Advertisement

sealed class RecommendationsScreenRoutes {
    class ToAd(val ad: Advertisement): RecommendationsScreenRoutes()
}