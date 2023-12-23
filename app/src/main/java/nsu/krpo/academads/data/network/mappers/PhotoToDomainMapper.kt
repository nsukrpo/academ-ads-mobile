package nsu.krpo.academads.data.network.mappers

import android.graphics.BitmapFactory
import android.graphics.drawable.BitmapDrawable
import nsu.krpo.academads.data.network.models.Photo
import nsu.krpo.academads.domain.model.ads.AdvertisementPhoto

class PhotoToDomainMapper {
    fun fromPhoto(photo: Photo): AdvertisementPhoto {
        return AdvertisementPhoto(BitmapDrawable(BitmapFactory.decodeByteArray(photo.image, 0, photo.image.size)))
    }
}