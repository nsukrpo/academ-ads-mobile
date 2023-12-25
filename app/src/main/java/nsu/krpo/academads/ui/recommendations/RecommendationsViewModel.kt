package nsu.krpo.academads.ui.recommendations

import android.graphics.drawable.BitmapDrawable
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import dagger.hilt.android.lifecycle.HiltViewModel
import nsu.krpo.academads.data.daos.recommendations.RecommendationsDao
import nsu.krpo.academads.data.daos.saved_info.SavedRep
import nsu.krpo.academads.domain.model.ads.Advertisement
import nsu.krpo.academads.ui.base.live_data.SingleLiveEvent
import nsu.krpo.academads.ui.base.live_data.update
import nsu.krpo.academads.ui.base.view.BaseViewModel
import nsu.krpo.academads.ui.recommendations.rv.AdvertisementWrapper
import javax.inject.Inject

@HiltViewModel
class RecommendationsViewModel @Inject constructor(
    savedRep: SavedRep,
    private val recommendationsDao: RecommendationsDao,
) : BaseViewModel() {

    private val _navEvent = SingleLiveEvent<RecommendationsScreenRoutes>()
    val navEvent: LiveData<RecommendationsScreenRoutes> = _navEvent

    private val _ads: MutableLiveData<List<AdvertisementWrapper>> = MutableLiveData()
    val ads: LiveData<List<AdvertisementWrapper>> = _ads

    private var userId: Long = 0L

    init {
        userId = savedRep.getSavedUserId()
        loadAds()
    }

    private fun loadAds() {
        recommendationsDao.getAllByUserId(userId)
            .setupDefaultSchedulers()
            .map {
                it.map {
                    AdvertisementWrapper(it, it.photos[0].photo)
                }
            }
            .subscribe { it ->
                _ads.value = it
            }.unsubscribeOnCleared()
    }

    fun onItemClicked(ad: Advertisement) {
        _navEvent.update { RecommendationsScreenRoutes.ToAd(ad) }
    }

    fun onItemLiked(ad: Advertisement) {
        //TODO
    }

    fun onItemDisliked(ad: Advertisement) {
        //TODO
    }
}