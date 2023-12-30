package nsu.krpo.academads.data.network.mappers

import nsu.krpo.academads.data.network.models.Avatar
import nsu.krpo.academads.data.network.models.UserResponse
import nsu.krpo.academads.domain.model.ads.User
import nsu.krpo.academads.domain.model.ads.UserType
import java.text.SimpleDateFormat
import java.time.Instant
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.ZoneId
import java.time.ZonedDateTime
import java.time.format.DateTimeFormatter
import java.util.Date
import java.util.EnumSet
import java.util.Locale

class UserToDomainMapper {
    fun fromResponse(response: UserResponse, avatar: Avatar): User {
        val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd")
        val dateTime = LocalDate.parse(response.regDate, formatter).atStartOfDay(ZoneId.systemDefault())
            return User(
                id = response.id,
                name = response.name,
                avatar = AvatarToDomainMapper().fromResponse(avatar),
                regDate = dateTime.toInstant(),
                type = EnumSet.of(UserType.USER)
            )
    }

}