package nsu.krpo.academads.ui.purchases

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import dagger.hilt.android.lifecycle.HiltViewModel
import nsu.krpo.academads.data.daos.purchases.PurchasesDao
import nsu.krpo.academads.data.daos.saved_info.SavedRep
import nsu.krpo.academads.domain.model.ads.Advertisement
import nsu.krpo.academads.ui.base.live_data.SingleLiveEvent
import nsu.krpo.academads.ui.base.live_data.update
import nsu.krpo.academads.ui.base.view.BaseViewModel
import nsu.krpo.academads.ui.purchases.rv.PurchaseWrapper
import javax.inject.Inject

@HiltViewModel
class PurchaseViewModel @Inject constructor(
    private val purchasesDao: PurchasesDao,
    private val savedRep: SavedRep,
) : BaseViewModel() {

    private val _navEvent: SingleLiveEvent<PurchasesScreenRoutes> = SingleLiveEvent()
    val navEvent: LiveData<PurchasesScreenRoutes> = _navEvent

    private val _purchases: MutableLiveData<List<PurchaseWrapper>> = MutableLiveData()
    val purchases: LiveData<List<PurchaseWrapper>> = _purchases

    private var userId = 1L

    init {
        userId = savedRep.getSavedUserId()
        loadAds()
    }

    private fun loadAds() {
        purchasesDao.getById(userId)
            .map {
                it.map { purchase ->
                    PurchaseWrapper(
                        purchase,
                        purchase.ads.photos[0].photo
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


    fun onItemClicked(ad: Advertisement) {
        _navEvent.update { PurchasesScreenRoutes.ToAd(ad) }
    }

    private fun onPurchasesResult(purchases: List<PurchaseWrapper>) {
        _purchases.value = purchases
    }




}