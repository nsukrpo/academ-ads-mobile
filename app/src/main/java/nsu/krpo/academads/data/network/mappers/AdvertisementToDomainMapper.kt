package nsu.krpo.academads.data.network.mappers

import nsu.krpo.academads.data.network.models.AdvertisementResponse
import nsu.krpo.academads.domain.model.ads.Advertisement
import nsu.krpo.academads.domain.model.ads.AdvertisementPhoto
import nsu.krpo.academads.domain.model.ads.AdvertisementStatus
import nsu.krpo.academads.domain.model.ads.Category
import nsu.krpo.academads.domain.model.ads.User
import java.math.BigDecimal
import java.time.ZonedDateTime

class AdvertisementToDomainMapper {

    fun fromResponse(response: AdvertisementResponse, category: Category, author: User, photos: List<AdvertisementPhoto>) : Advertisement {
        val id = response.id
        val header = response.header
        val description = response.description
        val price = BigDecimal.valueOf(response.price)

//        val sdf = SimpleDateFormat("dd.MM.yyyy HH:mm:ss:", Locale.getDefault())
        val publicationDate = ZonedDateTime.parse(response.publicationDate).toInstant()
        val editDate = ZonedDateTime.parse(response.editDate).toInstant()

        val countWatch = response.countWatch.toInt()

        val status = when (response.status) {
            "SENT_MODERATION" -> AdvertisementStatus.SENT_MODERATION
            "ON_ADS_BOARD" -> AdvertisementStatus.ON_ADS_BOARD
            "DECLINE_UNINFORMATIVE" -> AdvertisementStatus.DECLINE_UNINFORMATIVE
            "DECLINE_RUDE_WORDS" -> AdvertisementStatus.DECLINE_RUDE_WORDS
            "DECLINE_NUDITY" -> AdvertisementStatus.DECLINE_NUDITY
            "DECLINE_VIOLENCE" -> AdvertisementStatus.DECLINE_VIOLENCE
            "DECLINE_FRAUD" -> AdvertisementStatus.DECLINE_FRAUD
            "WITHDREW" -> AdvertisementStatus.WITHDREW
            "BOOKED" -> AdvertisementStatus.BOOKED
            "PURCHASED" -> AdvertisementStatus.PURCHASED
            else -> AdvertisementStatus.UNKNOWN
        }

        return Advertisement(
            id = id,
            header = header,
            description = description,
            price = price,
            category = category,
            author = author,
            publicationDate = publicationDate,
            countWatch = countWatch,
            status = status,
            editDate = editDate,
            photos = photos
        )
    }

}