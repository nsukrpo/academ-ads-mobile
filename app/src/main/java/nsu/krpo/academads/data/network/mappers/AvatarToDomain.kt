package nsu.krpo.academads.data.network.mappers

import nsu.krpo.academads.data.network.models.*
import nsu.krpo.academads.domain.model.ads.*

class AvatarToDomain {
    fun fromAvatar(avatar : Avatar) : UsersAvatar{
        return UserAvatar(
                id = /* сгенерировать */,
                photo = avatar.image
        )
    }
}