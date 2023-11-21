package nsu.krpo.academads.domain

import java.io.Serializable

data class AdvertisementPhoto(
    val ads: Advertisement,
    val photo: ByteArray
) : Serializable
