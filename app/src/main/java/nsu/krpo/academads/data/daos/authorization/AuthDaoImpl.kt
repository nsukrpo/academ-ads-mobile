package nsu.krpo.academads.data.daos.authorization

import io.reactivex.rxjava3.core.Single
import nsu.krpo.academads.data.network.AcademAdsAPIService
import nsu.krpo.academads.data.network.RetrofitInstance
import nsu.krpo.academads.data.network.models.ApiResponse
import nsu.krpo.academads.data.network.models.LoginCreate
import nsu.krpo.academads.data.network.models.TokenRequest
import nsu.krpo.academads.data.network.models.TokenResponse
import nsu.krpo.academads.domain.model.security.Credentials
import javax.inject.Inject

class AuthDaoImpl @Inject constructor(
    private val service: AcademAdsAPIService,
) : AuthDao {
    override fun sendRegistrationInfo(login : String, password : String) : Single<TokenResponse> {
        val tokenRequest = TokenRequest(login, password)
        return service.loginUser(tokenRequest)
    }

    override fun addRegistrationInfo(
        user: Long,
        login: String,
        password: String
    ): Single<ApiResponse> {
        val request = LoginCreate(user, login, password)
        return service.createLogin(request)
    }
}