package nsu.krpo.academads.data.daos.authorization

import io.reactivex.rxjava3.core.Single
import nsu.krpo.academads.data.network.models.ApiResponse
import nsu.krpo.academads.data.network.models.TokenResponse

interface AuthDao {
    fun sendRegistrationInfo(login : String, password : String) : Single<TokenResponse>
    fun addRegistrationInfo(user : Long, login : String, password: String) : Single<ApiResponse>
}