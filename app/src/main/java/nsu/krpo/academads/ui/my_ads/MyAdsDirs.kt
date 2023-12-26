package nsu.krpo.academads.ui.my_ads

import nsu.krpo.academads.domain.model.ads.Advertisement

sealed class MyAdsDirs {
    class ToMyAd(val ad: Advertisement): MyAdsDirs()
}