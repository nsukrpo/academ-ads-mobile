package nsu.krpo.academads.data.daos.users

import android.content.Context
import android.graphics.Bitmap
import androidx.core.graphics.drawable.toBitmap
import io.reactivex.rxjava3.core.Single
import nsu.krpo.academads.R
import nsu.krpo.academads.data.network.AcademAdsAPIService
import nsu.krpo.academads.data.network.mappers.UserToDomainMapper
import nsu.krpo.academads.data.network.models.Avatar
import nsu.krpo.academads.data.network.models.UserResponse
import nsu.krpo.academads.domain.model.ads.User
import java.io.ByteArrayOutputStream
import javax.inject.Inject

class UsersDaoWebImpl @Inject constructor(
    private val context: Context,
    private val webService: AcademAdsAPIService,
): UsersDao {
    override fun getById(id: Long): Single<User> = Single.fromCallable{getByIdBlocking(id)}

    private fun getByIdBlocking(id: Long): User {
        val userMapper = UserToDomainMapper()
        val userResponse = webService.getUserById(id).execute()
        val list = userResponse.body()!!
        return userMapper.fromResponse(
            list,
            getUserAvatarBlockingOrDefault(list)
        )
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

}