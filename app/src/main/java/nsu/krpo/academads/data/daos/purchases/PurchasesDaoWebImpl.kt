package nsu.krpo.academads.data.daos.purchases

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
import nsu.krpo.academads.data.network.mappers.PurchaseToDomainMapper
import nsu.krpo.academads.data.network.mappers.UserToDomainMapper
import nsu.krpo.academads.data.network.models.AdvertisementResponse
import nsu.krpo.academads.data.network.models.Avatar
import nsu.krpo.academads.data.network.models.Photo
import nsu.krpo.academads.data.network.models.UserResponse
import nsu.krpo.academads.domain.model.ads.AdvertisementPhoto
import nsu.krpo.academads.domain.model.ads.Purchase
import retrofit2.HttpException
import java.io.ByteArrayOutputStream
import javax.inject.Inject

class PurchasesDaoWebImpl @Inject constructor(
    private val context: Context,
    private val webService: AcademAdsAPIService,
) : PurchasesDao {
    override fun getById(userId: Long): Single<List<Purchase>> =
        Single.fromCallable { getByIdBlocking(userId) }

    private fun getByIdBlocking(userId: Long): List<Purchase> {
        val purchaseResponse = webService.getPurchases(userId).blockingGet()
        val list = purchaseResponse.map {
            val adResponse = webService.getAdvertisementById(it.ads).blockingGet()
            val sellerResponse = webService.getUserById(it.seller).execute().body()!!
            val buyerResponse = webService.getUserById(it.buyer).execute().body()!!
            val seller = UserToDomainMapper().fromResponse(
                sellerResponse,
                getUserAvatarBlockingOrDefault(sellerResponse)
            )
            val buyer = UserToDomainMapper().fromResponse(
                buyerResponse,
                getUserAvatarBlockingOrDefault(buyerResponse)
            )
            val ad = AdvertisementToDomainMapper().fromResponse(
                adResponse,
                CategoriesToDomainMapper().fromInt(adResponse.category),
                seller,
                listOf(getAdvPhotoBlockingOrDefault(adResponse))
            )
            PurchaseToDomainMapper().fromResponse(it, ad, seller, buyer)
        }
        return list
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
        return webService.getAvatarById(userResponse.avatar).blockingGet()
    }

    private fun getAdvPhotoBlockingOrDefault(advResponse: AdvertisementResponse): AdvertisementPhoto {
        if (advResponse.id == null) {
            val drawable = context.resources.getDrawable(R.drawable.camera)
            return AdvertisementPhoto(drawable)
        }

        try {
            val photoString = webService.getPhotoById(advResponse.id).blockingGet()
            val photo = Photo(photoString.bytes())
            return AdvertisementPhoto(
                BitmapDrawable(
                    null,
                    BitmapFactory.decodeByteArray(photo.image, 0, photo.image.size)
                )
            )
        } catch (e: Exception) {
            val drawable = context.resources.getDrawable(R.drawable.camera)
            return AdvertisementPhoto(drawable)
        }
    }
}