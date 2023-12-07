package nsu.krpo.academads.data.daos.users

import io.reactivex.rxjava3.core.Single
import nsu.krpo.academads.domain.model.ads.User

interface UsersDao {

    fun getById(id: Long): Single<User>
}