package nsu.krpo.academads.domain.model.ads

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import java.time.Instant
import java.util.Date
import java.util.EnumSet

@Parcelize
data class User(
    val id: Long,
    val name: String,
    val avatar: UsersAvatar,
    val regDate: Instant,
    val type: EnumSet<UserType>,
) : Parcelable