package nsu.krpo.academads.data.daos.users

import io.reactivex.rxjava3.core.Single
import nsu.krpo.academads.domain.model.ads.User
import nsu.krpo.academads.domain.model.ads.UserType
import nsu.krpo.academads.domain.model.ads.UsersAvatar
import java.time.Instant
import java.util.Date
import java.util.EnumSet
import javax.inject.Inject

class UsersDaoStubImpl @Inject constructor() : UsersDao {

    override fun getById(id: Long): Single<User> = Single.just(
        User(
            id,
            "Evgeny",
            UsersAvatar(byteArrayOf(1, 123, 56, 89)),
            Instant.now(),
            EnumSet.of(UserType.USER)
        ),
    )
}