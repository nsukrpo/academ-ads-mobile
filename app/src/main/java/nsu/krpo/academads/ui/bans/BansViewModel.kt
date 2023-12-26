package nsu.krpo.academads.ui.bans

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import dagger.hilt.android.lifecycle.HiltViewModel
import nsu.krpo.academads.data.daos.bans.BansDao
import nsu.krpo.academads.ui.bans.bans_rv.BanWrapper
import nsu.krpo.academads.ui.base.view.BaseViewModel
import nsu.krpo.academads.ui.categories.rv.CategoryWrapper
import java.util.Date
import javax.inject.Inject

@HiltViewModel
class BansViewModel @Inject constructor
    (
            private val bansDao: BansDao,
            ) : BaseViewModel(){

    private val _categories: MutableLiveData<List<BanWrapper>> = MutableLiveData()
    val categories: LiveData<List<BanWrapper>> = _categories

    init {
        getAllBans()
    }

    private fun getAllBans() {
        bansDao.getAll()
            .setupDefaultSchedulers()
            .map {
                it.map { BanWrapper(Date(it.blockDate.time), Date(it.blockDate.time + it.time), it.blockingReason) }
            }
            .subscribe {
                it -> _categories.value = it
            }.unsubscribeOnCleared()
    }
}