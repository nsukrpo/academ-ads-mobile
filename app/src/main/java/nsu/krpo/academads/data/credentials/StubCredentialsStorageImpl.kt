package nsu.krpo.academads.data.credentials

import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single
import nsu.krpo.academads.domain.model.security.Credentials
import nsu.krpo.academads.domain.repository.CredentialsStorage
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class StubCredentialsStorageImpl @Inject constructor() : CredentialsStorage {

    private var isConnected = false;
    override fun hasCredentials(): Single<Boolean> = Single.just(isConnected)

    override fun hasCredentialsBlocking(): Boolean = isConnected

    override fun saveCredentials(credentials: Credentials): Completable {
        return Completable.complete()
    }

    override fun saveCredentialsBlocking(credentials: Credentials) {

    }

    override fun getCredentials(): Single<Credentials> = Single.just(Credentials("user", "123"))

    override fun getCredentialsBlocking(): Credentials = Credentials("user", "12345")

    override fun clearCredentialsBlocking() {
        TODO("Not yet implemented")
    }

    override fun clearCredentials(): Completable {
        isConnected = false
        return Completable.complete()
    }
}