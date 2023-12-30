package nsu.krpo.academads.ui.auth

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import dagger.hilt.android.lifecycle.HiltViewModel
import nsu.krpo.academads.data.daos.authorization.AuthDao
import nsu.krpo.academads.data.daos.saved_info.SavedRep
import nsu.krpo.academads.ui.base.live_data.SingleLiveEvent
import nsu.krpo.academads.ui.base.live_data.update
import nsu.krpo.academads.ui.base.view.BaseViewModel
import javax.inject.Inject

@HiltViewModel
class AuthViewModel @Inject constructor(
    private val authDao: AuthDao,
    private val savedRep: SavedRep,
) : BaseViewModel() {

    private val _errorMessage: MutableLiveData<String> = MutableLiveData()
    val errorMessage: LiveData<String> = _errorMessage

    private val _navEvent = SingleLiveEvent<AuthScreenRoutes>()
    val navEvent: LiveData<AuthScreenRoutes> = _navEvent

    var token: String = "0"
    fun login(login: String, password: String) {
        authDao.sendRegistrationInfo(login, password).map {
            it.token
        }.setupDefaultSchedulers()
            .subscribe({
                token = it
                if (token != "0") {
                    savedRep.setSavedUserId(3)
                    _navEvent.update { AuthScreenRoutes.ToMainScreen() }
                } else {
                    _errorMessage.value = ERROR_USER
                }
            }, { error ->
                if (error.message.toString() == "HTTP 403 ") {
                    _errorMessage.value = ERROR_USER
                } else {
                    _errorMessage.value = ERROR_SERVER
                }
                Log.i("AUTH", error.message.toString())
            })

            .unsubscribeOnCleared()
    }

    fun registration() {
        _navEvent.update { AuthScreenRoutes.ToRegistration() }
    }

    companion object {
        val ERROR_SERVER = "Ошибка подключения"
        val ERROR_USER = "Неверный логин или пароль"
    }
}