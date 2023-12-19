package nsu.krpo.academads.data.network.mappers

import nsu.krpo.academads.data.network.models.Photo
import nsu.krpo.academads.domain.model.ads.AdvertisementPhoto

class PhotoToDomainMapper {
    fun fromPhoto(photo: Photo): AdvertisementPhoto {
        return AdvertisementPhoto(photo.image)
    }
}