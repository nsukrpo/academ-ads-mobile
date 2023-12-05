package nsu.krpo.academads.ui.profile

import nsu.krpo.academads.domain.model.ads.Advertisement

sealed interface ProfileScreenRoutes {

    class ToAd(ad: Advertisement): ProfileScreenRoutes
}