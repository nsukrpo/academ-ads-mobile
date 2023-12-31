package nsu.krpo.academads.ui.categories

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.rxjava3.core.Single
import nsu.krpo.academads.data.daos.advertisments.AdvertisementsDao
import nsu.krpo.academads.data.daos.categories.CategoriesDao
import nsu.krpo.academads.data.daos.purchases.PurchasesDao
import nsu.krpo.academads.domain.model.ads.Category
import nsu.krpo.academads.ui.base.live_data.SingleLiveEvent
import nsu.krpo.academads.ui.base.live_data.update
import nsu.krpo.academads.ui.base.view.BaseViewModel
import nsu.krpo.academads.ui.categories.rv.CategoryWrapper
import javax.inject.Inject

@HiltViewModel
class CategoriesViewModel @Inject constructor(
    private val categoriesDao: CategoriesDao,
    private val advertisementsDao: AdvertisementsDao,
    private val purchasesDao: PurchasesDao,
) : BaseViewModel() {

    private val _categories: MutableLiveData<List<CategoryWrapper>> = MutableLiveData()
    val categories: LiveData<List<CategoryWrapper>> = _categories

    private val _navEvent = SingleLiveEvent<CategoriesScreenRoots>()
    val navEvent: LiveData<CategoriesScreenRoots> = _navEvent

    private var allCategories: List<CategoryWrapper>? = null

    init {
        loadCategoriesList()

    }


    private fun loadCategoriesList() {


        categoriesDao.getAll().map { category ->
            category.map {

                CategoryWrapper(
                    it,
                    null
                )
            }
        }
            .setupDefaultSchedulers()
            .bindLoading()
            .subscribe(
                ::onCategoriesResult,
                ::onError,
            ).unsubscribeOnCleared()
    }

    private fun onCategoriesResult(categories: List<CategoryWrapper>) =
        _categories.update {
            allCategories = categories
            categories
        }

    fun onItemClicked(category: Category) =
        _navEvent.update { CategoriesScreenRoots.ToCategory(category) }
}