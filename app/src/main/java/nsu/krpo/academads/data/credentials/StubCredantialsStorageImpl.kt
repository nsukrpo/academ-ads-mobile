package nsu.krpo.academads.data.credentials

import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single
import nsu.krpo.academads.domain.model.security.Credentials
import nsu.krpo.academads.domain.repository.CredentialsStorage
import javax.inject.Inject

class StubCredantialsStorageImpl @Inject constructor() : CredentialsStorage {
    override fun hasCredentials(): Single<Boolean> = Single.just(true)

    override fun hasCredentialsBlocking(): Boolean = true

    override fun saveCredentials(credentials: Credentials): Completable {
        return Completable.complete()
    }

    override fun saveCredentialsBlocking(credentials: Credentials) {

    }

    override fun getCredentials(): Single<Credentials> = Single.just(Credentials("hi", "ha"))

    override fun getCredentialsBlocking(): Credentials = Credentials("hi", "ha")

    override fun clearCredentialsBlocking() {
        TODO("Not yet implemented")
    }

    override fun clearCredentials(): Completable {
        TODO("Not yet implemented")
    }
}