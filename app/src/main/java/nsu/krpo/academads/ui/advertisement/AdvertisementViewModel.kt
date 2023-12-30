package nsu.krpo.academads.ui.advertisement

import android.util.Log
import dagger.hilt.android.lifecycle.HiltViewModel
import nsu.krpo.academads.data.daos.advertisments.AdvertisementsDao
import nsu.krpo.academads.data.daos.saved_info.SavedRep
import nsu.krpo.academads.domain.model.ads.Advertisement
import nsu.krpo.academads.domain.model.ads.User
import nsu.krpo.academads.ui.base.view.BaseViewModel
import java.time.Instant
import java.time.Month
import java.time.ZoneId
import java.time.temporal.TemporalUnit
import java.util.Date
import javax.inject.Inject

@HiltViewModel
class AdvertisementViewModel @Inject constructor(
    private val savedRep: SavedRep,
    private val advertisementsDao: AdvertisementsDao,
): BaseViewModel() {
    var userId = 1L
    init {
        savedRep.getSavedUserId()
    }
//todo
    fun bookAd(ad: Advertisement) {
    /* вариант с началом следующих суток
    val tomorrowDate = Instant.now()
        .atZone(ZoneId.systemDefault())
        .plusDays(1)
        .toLocalDate()
        .atStartOfDay()
        .toInstant(ZoneOffset.UTC)*/
    val tomorrowDate = Instant.now().atZone(ZoneId.systemDefault()).plusDays(1).toInstant()
    advertisementsDao.book(ad, userId, tomorrowDate)
        .setupDefaultSchedulers()
        .subscribe (
            {},
            {
                Log.w("BOOK", it.message.toString())
            }
        ).unsubscribeOnCleared()

    }

    fun likeAd(ad: Advertisement) {
        advertisementsDao.like(ad, userId)
            .setupDefaultSchedulers()
            .subscribe(
                {

                },
                {
                    Log.i("LIKE", it.message.toString())
                }
            ).unsubscribeOnCleared()
    }

    fun dislikeAd(ad: Advertisement) {
        advertisementsDao.dislike(ad, userId)
            .setupDefaultSchedulers()
            .subscribe(
                {},
                {
                    Log.i("DISLIKE", it.message.toString())
                }
            ).unsubscribeOnCleared()
    }

    fun subscribe(user: User) {
        advertisementsDao.subscribe(user, userId)
    }
}