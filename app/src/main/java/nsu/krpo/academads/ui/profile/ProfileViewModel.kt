package nsu.krpo.academads.ui.profile

import android.graphics.BitmapFactory
import android.graphics.drawable.BitmapDrawable
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import dagger.hilt.android.lifecycle.HiltViewModel
import nsu.krpo.academads.data.daos.advertisments.AdvertisementsDao
import nsu.krpo.academads.data.daos.likes.LikesDao
import nsu.krpo.academads.data.daos.purchases.PurchasesDao
import nsu.krpo.academads.data.daos.saved_info.SavedRep
import nsu.krpo.academads.data.daos.users.UsersDao
import nsu.krpo.academads.domain.model.ads.Advertisement
import nsu.krpo.academads.domain.model.ads.User
import nsu.krpo.academads.ui.base.live_data.SingleLiveEvent
import nsu.krpo.academads.ui.base.live_data.update
import nsu.krpo.academads.ui.base.view.BaseViewModel
import nsu.krpo.academads.ui.profile.ads_rv.AdvertismentWrapper
import nsu.krpo.academads.ui.profile.likes_rv.LikedWrapper
import nsu.krpo.academads.ui.profile.purchases_rv.PurchaseWrapper
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(
    private val savedRep: SavedRep,
    private val usersDao: UsersDao,
    private val advertisementsDao: AdvertisementsDao,
    private val purchasesDao: PurchasesDao,
    private val likesDao: LikesDao,
) : BaseViewModel() {

    private val _navEvent = SingleLiveEvent<ProfileScreenRoutes>()
    val navEvent: LiveData<ProfileScreenRoutes> = _navEvent

    private val _user: MutableLiveData<User> = MutableLiveData()
    val user: LiveData<User> = _user

    private val _ads: MutableLiveData<List<AdvertismentWrapper>> = MutableLiveData()
    val ads: LiveData<List<AdvertismentWrapper>> = _ads

    private val _purchases: MutableLiveData<List<PurchaseWrapper>> = MutableLiveData()
    val purchases: LiveData<List<PurchaseWrapper>> = _purchases

    private val _likes: MutableLiveData<List<LikedWrapper>> = MutableLiveData()
    val likes: LiveData<List<LikedWrapper>> = _likes

    private var userId: Long = 0L
    var myAdsList = listOf<AdvertismentWrapper>()

    init {
        userId = savedRep.getSavedUserId()
        loadProfileInfo()
        loadMyAdvertisements()
        loadMyPurchases()
        loadMyLikes()
    }

    fun onAdItemClicked(ad: Advertisement) = _navEvent.update { ProfileScreenRoutes.ToMyAd(ad) }

    fun onPurchaseItemClicked(ad: Advertisement) = _navEvent.update { ProfileScreenRoutes.ToAd(ad) }

    fun onLikedItemClicked(ad: Advertisement) = _navEvent.update { ProfileScreenRoutes.ToAd(ad) }

    fun onMyItems() = _navEvent.update { ProfileScreenRoutes.ToMyPurchases() }

    fun onMyAds() = _navEvent.update { ProfileScreenRoutes.ToMyAds() }

    fun onMyLikes() = _navEvent.update { ProfileScreenRoutes.ToLikes() }

    fun onCreateAd() = _navEvent.update { ProfileScreenRoutes.ToCreateAd() }

    fun onBans() = _navEvent.update { ProfileScreenRoutes.ToBans() }

    private fun loadProfileInfo() {
        usersDao.getById(userId)
            .setupDefaultSchedulers()
            .bindLoading()
            .subscribe(
                ::onLoadProfileResult,
                ::onError
            ).unsubscribeOnCleared()

    }

    private fun loadMyAdvertisements() {
        advertisementsDao.getAllById(userId)
            .map {
                it.map { advertisement ->
                    AdvertismentWrapper(
                        advertisement,
                        BitmapDrawable(
                            BitmapFactory.decodeByteArray(
                                advertisement.photos[0].photo,
                                0,
                                advertisement.photos[0].photo.size
                            )
                        ),
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

    private fun loadMyPurchases() {
        purchasesDao.getById(userId)
            .map {
                it.map { purchase ->
                    PurchaseWrapper(
                        purchase,
                        BitmapDrawable(
                            BitmapFactory.decodeByteArray(
                                purchase.ads.photos[0].photo,
                                0,
                                purchase.ads.photos[0].photo.size
                            )
                        )
                    )
                }
            }
            .setupDefaultSchedulers()
            .bindLoading()
            .subscribe(
                ::onPurchasesResult,
                ::onError
            ).unsubscribeOnCleared()
    }

    private fun loadMyLikes() {
        likesDao.getById(userId)
            .map {
                it.map { advertisement ->
                    LikedWrapper(
                        advertisement,
                        BitmapDrawable(
                            BitmapFactory.decodeByteArray(
                                advertisement.photos[0].photo,
                                0,
                                advertisement.photos[0].photo.size
                            )
                        )
                    )
                }
            }
            .setupDefaultSchedulers()
            .bindLoading()
            .subscribe(
                ::onLikesResult,
                ::onError
            ).unsubscribeOnCleared()
    }

    private fun onLoadProfileResult(user: User) {
        _user.value = user
    }

    private fun onAdvertisementsResult(ads: List<AdvertismentWrapper>) {
        myAdsList = ads
        _ads.value = ads
    }

    private fun onPurchasesResult(purchases: List<PurchaseWrapper>) {
        _purchases.value = purchases
    }

    private fun onLikesResult(likes: List<LikedWrapper>) {
        _likes.value = likes
    }
}