package nsu.krpo.academads.data.daos.authorization

import io.reactivex.rxjava3.core.Single
import nsu.krpo.academads.data.network.models.ApiResponse
import nsu.krpo.academads.data.network.models.TokenResponse
import nsu.krpo.academads.domain.repository.CredentialsStorage
import javax.inject.Inject

class AuthDaoStubImpl @Inject constructor(
    private val credentialsStorage: CredentialsStorage,
): AuthDao {

    override fun sendRegistrationInfo(login: String, password: String): Single<TokenResponse> {
        val creds = credentialsStorage.getCredentialsBlocking()
        if ((login == creds.login) && (password == creds.password)) {
            return Single.just(TokenResponse(1234))
        } else
            return Single.just(TokenResponse(0))
    }

    override fun addRegistrationInfo(
        user: Long,
        login: String,
        password: String
    ): Single<ApiResponse> {
        TODO("Not yet implemented")
    }
}