package nsu.krpo.academads.data.network.mappers

import nsu.krpo.academads.data.network.models.*
import nsu.krpo.academads.domain.model.ads.*

class UserToDomainMapper {
    fun fromResponse(response: UserResponse): User {
        val regDate = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).parse(response.regDate)
        val userType = /* как заполнять? */

        return User(
                id = response.id,
                name = response.name,
                avatar = UsersAvatar(response.avatar),
                regDate = regDate ?: Date(),
                type = userType
        )
    }
    fun fromCreate(create: UserCreate): User {
        return User(
                id = /* сгенерировать */,
                name = create.name,
                avatar = null,
                regDate = Date(),
                type = /* ??? */
        )
    }

    fun fromUpdate(update: UserUpdate, user_id: Long): User {
        return User (
                id = /* получить */,
                name = create.name,
                avatar = UsersAvatar(update.avatar),
                regDate = /* получить */,
                type = /* ??? */
        )

    }
}