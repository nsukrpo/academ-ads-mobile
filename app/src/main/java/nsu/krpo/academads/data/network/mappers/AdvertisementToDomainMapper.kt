package nsu.krpo.academads.data.network.mappers

import nsu.krpo.academads.data.network.models.*
import nsu.krpo.academads.domain.model.ads.*

class AdvertisementToDomainMapper {

    fun fromResponse(response: AdvertisementResponse) : Advertisement{
        val id = response.id
        val header = response.header
        val description = response.description
        val price = BigDecimal.valueOf(response.price)
        val category = Category(id = response.category)
        val author = User(id = response.author)

        val sdf = SimpleDateFormat("dd.MM.yyyy HH:mm", Locale.getDefault())
        val publicationDate = Timestamp(sdf.parse(response.publicationDate).time)
        val editDate = Timestamp(sdf.parse(response.editDate).time)

        val countWatch = response.countWatch.toInt()

        val status = when (response.status) {
            "SOLD" -> AdvertisementStatus.SOLD
            "ACTIVE" -> AdvertisementStatus.ACTIVE
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
                photos = ArrayList()
        )
    }
    fun fromCreate(create : AdvertisementCreate){
        val category = Category(create.category)
        val author = User(create.author)
        val publicationDate = Timestamp(System.currentTimeMillis())
        val countWatch = 0
        val status = AdvertisementStatus.MODERATING
        val editDate = Timestamp(System.currentTimeMillis())
        val photos = null

        return Advertisement(
                id = /* сгенерировать */,
                header = сreate.header,
                description = сreate.description,
                price = BigDecimal.valueOf(advertisementCreate.price),
                category = category,
                author = author,
                publicationDate = publicationDate,
                countWatch = countWatch,
                status = status,
                editDate = editDate,
                photos = photos
        )
    }
    fun fromUpdate(update : AdvertisementUpdate){
        val category = Category(update.category)
        val author = User(/* достать из бд */)
        val publicationDate = Timestamp(System.currentTimeMillis())
        val countWatch = 0 /* достать из бд */
        val status = AdvertisementStatus(update.status)
        val editDate = Timestamp(System.currentTimeMillis())
        val photos = /* достать из бд */

        return Advertisement(
                id = update.id,
                header = update.header,
                description = update.description,
                price = BigDecimal.valueOf(update.price),
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
