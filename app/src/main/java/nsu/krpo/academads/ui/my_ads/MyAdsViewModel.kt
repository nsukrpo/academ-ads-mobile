package nsu.krpo.academads.ui.my_ads

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import dagger.hilt.android.lifecycle.HiltViewModel
import nsu.krpo.academads.data.daos.advertisments.AdvertisementsDao
import nsu.krpo.academads.data.daos.saved_info.SavedRep
import nsu.krpo.academads.domain.model.ads.Advertisement
import nsu.krpo.academads.ui.base.live_data.SingleLiveEvent
import nsu.krpo.academads.ui.base.live_data.update
import nsu.krpo.academads.ui.base.view.BaseViewModel
import nsu.krpo.academads.ui.category.CategoryScreenRoutes
import nsu.krpo.academads.ui.my_ads.rv.AdWrapper
import nsu.krpo.academads.ui.profile.ads_rv.AdvertismentWrapper
import javax.inject.Inject

@HiltViewModel
class MyAdsViewModel @Inject constructor(
    private val advertisementsDao: AdvertisementsDao,
    savedRep: SavedRep,
) : BaseViewModel() {

    private val _navEvent = SingleLiveEvent<MyAdsDirs>()
    val navEvent: LiveData<MyAdsDirs> = _navEvent

    fun onItemClicked(ad: Advertisement) {
        _navEvent.update { MyAdsDirs.ToMyAd(ad) }
    }

    private val _ads: MutableLiveData<List<AdWrapper>> = MutableLiveData()
    val ads: LiveData<List<AdWrapper>> = _ads

    var userId: Long = 1L

    var myAdsList = listOf<AdWrapper>()

    init {
        userId = savedRep.getSavedUserId()
        getAds()
    }

    private fun getAds() {
        advertisementsDao.getAllById(userId)
            .map {
                it.map { advertisement ->
                    AdWrapper(
                        advertisement,
                        advertisement.photos[0].photo,
                    )
                }
            }
            .setupDefaultSchedulers()
            .bindLoading()
            .subscribe(
                ::onAdvertisementsResult,
                ::onError
            ).unsubscribeOnCleared()
    }

    private fun onAdvertisementsResult(ads: List<AdWrapper>) {
        myAdsList = ads
        _ads.value = ads
    }

}