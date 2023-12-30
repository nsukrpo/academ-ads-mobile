package nsu.krpo.academads.ui.bans

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import dagger.hilt.android.lifecycle.HiltViewModel
import nsu.krpo.academads.data.daos.bans.BansDao
import nsu.krpo.academads.data.daos.saved_info.SavedRep
import nsu.krpo.academads.ui.bans.bans_rv.BanWrapper
import nsu.krpo.academads.ui.base.view.BaseViewModel
import nsu.krpo.academads.ui.categories.rv.CategoryWrapper
import java.time.ZonedDateTime
import java.util.Date
import javax.inject.Inject

@HiltViewModel
class BansViewModel @Inject constructor
    (
    private val bansDao: BansDao,
    private val savedRep: SavedRep,
) : BaseViewModel() {

    private val _categories: MutableLiveData<List<BanWrapper>> = MutableLiveData()
    val categories: LiveData<List<BanWrapper>> = _categories
    var userId = 1L

    init {
        userId = savedRep.getSavedUserId()
        getAllBans()
    }

    private fun getAllBans() {
        bansDao.getAllByUseId(userId)
            .setupDefaultSchedulers()
            .map { it ->
                it.map {
                    BanWrapper(
                        it.blockDate,
                        it.blockDate.plusMillis(it.time.toLong()),
                        it.blockingReason
                    )
                }
            }
            .subscribe { it ->
                _categories.value = it
            }.unsubscribeOnCleared()
    }
}