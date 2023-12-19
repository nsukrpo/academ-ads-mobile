package nsu.krpo.academads.ui.my_advertisement

import nsu.krpo.academads.domain.model.ads.Advertisement

sealed interface MyAdvertisementScreenRoutes {

    class ToEditAd(val ad: Advertisement): MyAdvertisementScreenRoutes
}