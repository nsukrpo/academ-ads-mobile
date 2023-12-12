

class PhotoToDomainMapper {
    fun fromPhoto(photo : Photo) : AdvertisementPhoto(){
        return AdvertisementPhoto(photo.image)
    }
}