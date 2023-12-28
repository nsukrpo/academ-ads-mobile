package nsu.krpo.academads.ui.edit_item

import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Drawable
import androidx.core.graphics.drawable.toBitmap
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import dagger.hilt.android.lifecycle.HiltViewModel
import nsu.krpo.academads.data.daos.advertisments.AdvertisementsDao
import nsu.krpo.academads.domain.model.ads.Advertisement
import nsu.krpo.academads.domain.model.ads.AdvertisementPhoto
import nsu.krpo.academads.domain.model.ads.AdvertisementStatus
import nsu.krpo.academads.domain.model.ads.Category
import nsu.krpo.academads.ui.base.live_data.SingleLiveEvent
import nsu.krpo.academads.ui.base.live_data.update
import nsu.krpo.academads.ui.base.view.BaseViewModel
import nsu.krpo.academads.ui.edit_item.photos_rv.ItemPhotoWrapper
import java.math.BigDecimal
import java.sql.Timestamp
import java.time.Instant
import javax.inject.Inject


@HiltViewModel
class EditViewModel @Inject constructor(
    private val advertisementsDao: AdvertisementsDao,
) : BaseViewModel() {

    private val _navEvent = SingleLiveEvent<EditScreenRoutes>()
    val navEvent: LiveData<EditScreenRoutes> = _navEvent

    private val _photo: MutableLiveData<List<ItemPhotoWrapper>> = MutableLiveData()
    val photo: LiveData<List<ItemPhotoWrapper>> = _photo


    private val myPhotos: MutableList<ItemPhotoWrapper> = mutableListOf()


    fun providePhotos(photos: List<ItemPhotoWrapper>) {
        photos.forEach {
            myPhotos.add(it)
        }
    }

    fun addPhotoToList(photo: Drawable) {
        myPhotos.add(ItemPhotoWrapper(BitmapDrawable(photo.toBitmap()) ))
        _photo.value = myPhotos
    }

    fun onRemoveButtonClicked(position: Int) {
        myPhotos.removeAt(position)
        _photo.value = myPhotos
    }

    fun onModeration(
        oldAd: Advertisement,
        header: String,
        description: String,
        price: BigDecimal,
        category: Category,
        status: AdvertisementStatus
    ) {
        advertisementsDao.editAd(
            oldAd.id, header, description, price, category, status
        )
        val newAd = Advertisement(
            oldAd.id,
            header,
            description,
            price,
            category,
            oldAd.author,
            oldAd.publicationDate,
            oldAd.countWatch,
            status,
            Instant.now(),
            myPhotos.map {
                AdvertisementPhoto(
                    it.photo
                )
            }
        )
        _navEvent.update { EditScreenRoutes.ToMyAd(newAd) }
    }
}