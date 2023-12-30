package nsu.krpo.academads.ui.my_likes

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import dagger.hilt.android.lifecycle.HiltViewModel
import nsu.krpo.academads.data.daos.advertisments.AdvertisementsDao
import nsu.krpo.academads.data.daos.likes.LikesDao
import nsu.krpo.academads.data.daos.saved_info.SavedRep
import nsu.krpo.academads.domain.model.ads.Advertisement
import nsu.krpo.academads.ui.base.live_data.SingleLiveEvent
import nsu.krpo.academads.ui.base.live_data.update
import nsu.krpo.academads.ui.base.view.BaseViewModel
import nsu.krpo.academads.ui.my_likes.rv.LikedAdWrapper
import javax.inject.Inject

@HiltViewModel
class MyLikesViewModel @Inject constructor(
    private val likesDao: LikesDao,
    private val advertisementsDao: AdvertisementsDao,
    private val savedRep: SavedRep,
): BaseViewModel() {

    private val _ads: MutableLiveData<List<LikedAdWrapper>> = MutableLiveData()
    val ads: LiveData<List<LikedAdWrapper>> = _ads

    private val _navEvent: SingleLiveEvent<MyLikesScreenRoutes> = SingleLiveEvent()
    val navEvent: LiveData<MyLikesScreenRoutes> = _navEvent

    private var userId = 1L

    init {
        userId = savedRep.getSavedUserId()
        loadAds()
    }

    private fun loadAds() {
        likesDao.getById(userId)
            .map { it.map {
                LikedAdWrapper(it, it.photos[0].photo)
            } }
            .setupDefaultSchedulers()
            .subscribe(
                {
                    list ->
                    _ads.value = list
                },
                {
                  Log.i("LIKES", it.message.toString())
                }
            )
            .unsubscribeOnCleared()
    }

    fun onItemClicked(ad: Advertisement) {
        _navEvent.update { MyLikesScreenRoutes.ToAd(ad) }
    }

    fun onItemDisliked(ad: Advertisement) {
        advertisementsDao.dislike(ad, userId)
        loadAds()
    }

}