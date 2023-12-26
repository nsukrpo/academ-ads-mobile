package nsu.krpo.academads.ui.advertisement

import dagger.hilt.android.lifecycle.HiltViewModel
import nsu.krpo.academads.data.daos.advertisments.AdvertisementsDao
import nsu.krpo.academads.data.daos.saved_info.SavedRep
import nsu.krpo.academads.domain.model.ads.Advertisement
import nsu.krpo.academads.domain.model.ads.User
import nsu.krpo.academads.ui.base.view.BaseViewModel
import java.util.Date
import javax.inject.Inject

@HiltViewModel
class AdvertisementViewModel @Inject constructor(
    private val savedRep: SavedRep,
    private val advertisementsDao: AdvertisementsDao,
): BaseViewModel() {
    var userId = 0L
    init {
        savedRep.getSavedUserId()
    }
//todo
    fun bookAd(ad: Advertisement) {
        val bookStatus = advertisementsDao.book(ad, userId,  Date())

    }

    fun likeAd(ad: Advertisement) {
        advertisementsDao.like(ad, userId)
    }

    fun subscribe(user: User) {
        advertisementsDao.subscribe(user, userId)
    }
}