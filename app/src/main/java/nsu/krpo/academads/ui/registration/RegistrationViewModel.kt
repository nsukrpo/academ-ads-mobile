package nsu.krpo.academads.ui.registration

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import dagger.hilt.android.lifecycle.HiltViewModel
import nsu.krpo.academads.data.daos.authorization.AuthDao
import nsu.krpo.academads.ui.base.live_data.SingleLiveEvent
import nsu.krpo.academads.ui.base.live_data.update
import nsu.krpo.academads.ui.base.view.BaseViewModel
import nsu.krpo.academads.ui.purchases.PurchasesScreenRoutes
import javax.inject.Inject

@HiltViewModel
class RegistrationViewModel @Inject constructor(
    private val authDao: AuthDao,
) : BaseViewModel() {

    private val _tokenResponse: MutableLiveData<Long> = MutableLiveData()
    val tokenResponse: LiveData<Long> = _tokenResponse

    private val _navEvent: SingleLiveEvent<RegScreenRoutes> = SingleLiveEvent()
    val navEvent: LiveData<RegScreenRoutes> = _navEvent

    private var token: Long = 0L

    fun registration(login: String, password: String) {
        authDao.addRegistrationInfo(0, login, password)
            .setupDefaultSchedulers()
            .subscribe( {
                Log.i("REG", it.toString())
                _navEvent.update { RegScreenRoutes.ToAuth() }
            },
                {
                    Log.i("REG", it.message.toString())
                    _navEvent.update { RegScreenRoutes.ToAuth() }
                }).unsubscribeOnCleared()
    }
}