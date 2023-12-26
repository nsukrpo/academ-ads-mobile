package nsu.krpo.academads.domain.model.ads

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class UsersAvatar(
        val photo: ByteArray
) : Parcelable
