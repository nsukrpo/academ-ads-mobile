package nsu.krpo.academads.data.network.mappers

import nsu.krpo.academads.data.network.models.Avatar
import nsu.krpo.academads.domain.model.ads.UsersAvatar

class AvatarToDomainMapper {
    fun fromResponse(avatar: Avatar): UsersAvatar {
        return UsersAvatar(avatar.image)
    }
}