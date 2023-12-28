package nsu.krpo.academads.ui.category

import android.graphics.drawable.BitmapDrawable
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import dagger.hilt.android.lifecycle.HiltViewModel
import nsu.krpo.academads.data.daos.advertisments.AdvertisementsDao
import nsu.krpo.academads.data.daos.saved_info.SavedRep
import nsu.krpo.academads.domain.model.ads.Advertisement
import nsu.krpo.academads.domain.model.ads.Category
import nsu.krpo.academads.ui.base.live_data.SingleLiveEvent
import nsu.krpo.academads.ui.base.live_data.update
import nsu.krpo.academads.ui.base.view.BaseViewModel
import nsu.krpo.academads.ui.categories.CategoriesScreenRoots
import nsu.krpo.academads.ui.categories.rv.CategoryWrapper
import nsu.krpo.academads.ui.category.rv.AdvertisementWrapper
import javax.inject.Inject

@HiltViewModel
class CategoryViewModel @Inject constructor(
    private val advertisementsDao: AdvertisementsDao,
    private val savedRep: SavedRep,
) : BaseViewModel() {
    private val _ads: MutableLiveData<List<AdvertisementWrapper>> = MutableLiveData()
    val ads: LiveData<List<AdvertisementWrapper>> = _ads

    private val _navEvent = SingleLiveEvent<CategoryScreenRoutes>()
    val navEvent: LiveData<CategoryScreenRoutes> = _navEvent

    private var category: Category = Category.OTHER
    private var userId: Long = 1L

    init {
    //    userId = savedRep.getSavedUserId()
    }

    fun provideCategory(category: Category) {
        this.category = category
        loadAds()
    }

    fun onItemClicked(ad: Advertisement) {
        _navEvent.update { CategoryScreenRoutes.ToAd(ad) }
    }

    fun onItemLiked(ad: Advertisement) {
        advertisementsDao.like(ad, userId)
            .setupDefaultSchedulers()
            .subscribe(
                {},
                {
                    error ->
                    Log.i("LIKE", error.message!!)
                }
            )
            .unsubscribeOnCleared()
    }

    fun onItemDisliked(ad: Advertisement) {
        //TODO
    }

    private fun loadAds() {
        advertisementsDao.getAllByCategory(category)
            .setupDefaultSchedulers()
            .map { it.map { AdvertisementWrapper(it, it.photos[0].photo) } }
            .subscribe({
                _ads.value = it

            },
                { error ->
                    Log.i("AD", error.message.toString())
                }).unsubscribeOnCleared()
    }
}