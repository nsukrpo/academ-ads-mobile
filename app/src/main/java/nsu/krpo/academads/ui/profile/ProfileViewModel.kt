package nsu.krpo.academads.ui.profile

import android.graphics.BitmapFactory
import android.graphics.drawable.BitmapDrawable
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import dagger.hilt.android.lifecycle.HiltViewModel
import nsu.krpo.academads.data.daos.advertisments.AdvertisementsDao
import nsu.krpo.academads.data.daos.saved_info.SavedRep
import nsu.krpo.academads.data.daos.users.UsersDao
import nsu.krpo.academads.domain.model.ads.Advertisement
import nsu.krpo.academads.domain.model.ads.User
import nsu.krpo.academads.ui.base.live_data.SingleLiveEvent
import nsu.krpo.academads.ui.base.live_data.update
import nsu.krpo.academads.ui.base.view.BaseViewModel
import nsu.krpo.academads.ui.profile.ads_rv.AdvertismentWrapper
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(
    private val savedRep: SavedRep,
    private val usersDao: UsersDao,
    private val advertisementsDao: AdvertisementsDao,

    ) : BaseViewModel() {

    private val _navEvent = SingleLiveEvent<ProfileScreenRoutes>()
    val navEvent: LiveData<ProfileScreenRoutes> = _navEvent

    private val _user: MutableLiveData<User> = MutableLiveData()
    val user: LiveData<User> = _user

    private val _ads: MutableLiveData<List<AdvertismentWrapper>> = MutableLiveData()
    val ads: LiveData<List<AdvertismentWrapper>> = _ads

    private var userId: Long = 0L
    var myAdsList = listOf<AdvertismentWrapper>()

    init {
        userId = savedRep.getSavedUserId()
        loadProfileInfo()
        loadMyAdvertisments()
    }

    fun onItemClicked(ad: Advertisement) = _navEvent.update { ProfileScreenRoutes.ToAd(ad) }

    private fun loadProfileInfo() {
        usersDao.getById(userId)
            .setupDefaultSchedulers()
            .bindLoading()
            .subscribe(
                ::onLoadProfileResult,
                ::onError
            ).unsubscribeOnCleared()

    }

    private fun loadMyAdvertisments() {
        advertisementsDao.getAllById(userId)
            .map {
                it.map { advertisement ->
                    AdvertismentWrapper(
                        BitmapDrawable(
                            BitmapFactory.decodeByteArray(
                                advertisement.photos[0].photo,
                                0,
                                advertisement.photos[0].photo.size
                            )
                        ),
                        advertisement.header,
                        advertisement.status
                    )
                }
            }
            .setupDefaultSchedulers()
            .bindLoading()
            .subscribe(
                ::onAdvertismentsResult,
                ::onError
            ).unsubscribeOnCleared()
    }

    private fun onLoadProfileResult(user: User) {
        _user.value = user
    }

    private fun onAdvertismentsResult(ads: List<AdvertismentWrapper>) {
        myAdsList = ads
        _ads.value = ads
    }
}