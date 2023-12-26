package nsu.krpo.academads.ui.edit_item

import nsu.krpo.academads.domain.model.ads.Advertisement

sealed interface EditScreenRoutes {

    class ToMyAd(val ad: Advertisement): EditScreenRoutes
}