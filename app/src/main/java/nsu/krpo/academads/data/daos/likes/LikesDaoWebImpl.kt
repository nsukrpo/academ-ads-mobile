package nsu.krpo.academads.data.daos.likes

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.drawable.BitmapDrawable
import androidx.core.graphics.drawable.toBitmap
import io.reactivex.rxjava3.core.Single
import nsu.krpo.academads.R
import nsu.krpo.academads.data.network.AcademAdsAPIService
import nsu.krpo.academads.data.network.mappers.AdvertisementToDomainMapper
import nsu.krpo.academads.data.network.mappers.CategoriesToDomainMapper
import nsu.krpo.academads.data.network.mappers.UserToDomainMapper
import nsu.krpo.academads.data.network.models.AdvertisementResponse
import nsu.krpo.academads.data.network.models.Avatar
import nsu.krpo.academads.data.network.models.Photo
import nsu.krpo.academads.data.network.models.UserResponse
import nsu.krpo.academads.domain.model.ads.Advertisement
import nsu.krpo.academads.domain.model.ads.AdvertisementPhoto
import retrofit2.HttpException
import java.io.ByteArrayOutputStream
import javax.inject.Inject

class LikesDaoWebImpl @Inject constructor(
    private val context: Context,
    private val webAPIService: AcademAdsAPIService,
) : LikesDao {
    override fun getById(userId: Long): Single<List<Advertisement>> =
        Single.fromCallable { getByIdBlocking(userId) }

    private fun getByIdBlocking(userId: Long): List<Advertisement> {
        val ads = webAPIService.getFavoriteAdvertisements(userId).blockingGet()
        return ads.map {
            val userResponse = webAPIService.getUserById(it.author).execute().body()!!
            val user = UserToDomainMapper().fromResponse(
                userResponse,
                getUserAvatarBlockingOrDefault(userResponse)
            )
            AdvertisementToDomainMapper().fromResponse(
                it,
                CategoriesToDomainMapper().fromInt(it.category),
                user,
                listOf(getAdvPhotoBlockingOrDefault(it))

            )
        }
    }

    private fun getUserAvatarBlockingOrDefault(userResponse: UserResponse): Avatar {
        if (userResponse.avatar == null) {
            val drawable = context.resources.getDrawable(R.drawable.seller)
            val st = ByteArrayOutputStream()
            drawable.toBitmap(drawable.intrinsicHeight, drawable.intrinsicWidth)
                .compress(Bitmap.CompressFormat.PNG, 100, st)
            return Avatar(st.toByteArray())
        }

        //TODO add try/catch if Avatar by ID not found
        return webAPIService.getAvatarById(userResponse.avatar).blockingGet()
    }

    private fun getAdvPhotoBlockingOrDefault(advResponse: AdvertisementResponse): AdvertisementPhoto {
        if (advResponse.id == null) {
            val drawable = context.resources.getDrawable(R.drawable.camera)
            return AdvertisementPhoto(drawable)
        }

        var photo: Photo;
        try {
            photo = webAPIService.getPhotoById(advResponse.id).blockingGet()
            return AdvertisementPhoto(
                BitmapDrawable(
                    null,
                    BitmapFactory.decodeByteArray(photo.image, 0, photo.image.size)
                )
            )
        } catch (e: HttpException) {
            val drawable = context.resources.getDrawable(R.drawable.camera)
            return AdvertisementPhoto(drawable)
        }
    }
}