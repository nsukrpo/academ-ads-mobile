package nsu.krpo.academads.data.network.mappers

import nsu.krpo.academads.data.network.models.Avatar
import nsu.krpo.academads.data.network.models.UserResponse
import nsu.krpo.academads.domain.model.ads.User
import nsu.krpo.academads.domain.model.ads.UserType
import java.text.SimpleDateFormat
import java.util.Date
import java.util.EnumSet
import java.util.Locale

class UserToDomainMapper {
    fun fromResponse(response: UserResponse, avatar: Avatar): User {
            return User(
                id = response.id,
                name = response.name,
                avatar = AvatarToDomainMapper().fromResponse(avatar),
                regDate = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).parse(response.regDate) ?: Date(),
                type = EnumSet.of(UserType.USER)
            )
    }

}