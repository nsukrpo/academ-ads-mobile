package nsu.krpo.academads.ui.my_advertisement

import androidx.lifecycle.LiveData
import dagger.hilt.android.lifecycle.HiltViewModel
import nsu.krpo.academads.data.daos.advertisments.AdvertisementsDao
import nsu.krpo.academads.domain.model.ads.Advertisement
import nsu.krpo.academads.domain.model.ads.AdvertisementStatus
import nsu.krpo.academads.ui.base.live_data.SingleLiveEvent
import nsu.krpo.academads.ui.base.live_data.update
import nsu.krpo.academads.ui.base.view.BaseViewModel
import javax.inject.Inject

@HiltViewModel
class MyAdvertisementViewModel @Inject constructor(
    private val advertisementsDao: AdvertisementsDao,
): BaseViewModel() {

    private val _navEvent = SingleLiveEvent<MyAdvertisementScreenRoutes>()
    val navEvent: LiveData<MyAdvertisementScreenRoutes> = _navEvent

    fun changeAdToSold(ad: Advertisement) {
        advertisementsDao.changeAdStatus(ad, AdvertisementStatus.SOLD)
    }

    fun changeAdToDeleted(ad: Advertisement) {
        advertisementsDao.changeAdStatus(ad, AdvertisementStatus.WITHDRAWN_FROM_SALE)
    }

    fun onEdit(ad: Advertisement) {
        _navEvent.update { MyAdvertisementScreenRoutes.ToEditAd(ad) }
    }
}