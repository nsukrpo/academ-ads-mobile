package nsu.krpo.academads.data.credentials

import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single
import nsu.krpo.academads.domain.model.security.Credentials
import nsu.krpo.academads.domain.repository.CredentialsStorage
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ImMemoryCredentialsStorageImpl @Inject constructor() : CredentialsStorage {

    private var credentials: Credentials? = null

    override fun hasCredentials(): Single<Boolean> = Single.just(hasCredentialsBlocking())

    override fun hasCredentialsBlocking(): Boolean = credentials != null

    override fun saveCredentials(credentials: Credentials): Completable =
        Completable.fromAction { saveCredentialsBlocking(credentials) }

    override fun saveCredentialsBlocking(credentials: Credentials) {
        this.credentials = credentials
    }

    override fun getCredentials(): Single<Credentials> = Single.just(getCredentialsBlocking())

    override fun getCredentialsBlocking(): Credentials =
        credentials ?: error("Trying to access empty credentials")

    override fun clearCredentialsBlocking() {
        credentials = null
    }

    override fun clearCredentials(): Completable =
        Completable.fromAction { clearCredentialsBlocking() }
}