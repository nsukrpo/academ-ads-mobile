package nsu.krpo.academads.domain.repository

import nsu.krpo.academads.domain.model.security.Credentials
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single

interface CredentialsStorage {

    fun hasCredentials(): Single<Boolean>

    fun hasCredentialsBlocking(): Boolean

    fun saveCredentials(credentials: Credentials): Completable

    fun saveCredentialsBlocking(credentials: Credentials)

    fun getCredentials(): Single<Credentials>

    fun getCredentialsBlocking(): Credentials

    fun clearCredentialsBlocking()

    fun clearCredentials(): Completable
}