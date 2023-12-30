package nsu.krpo.academads.ui.create_ad

import android.graphics.Bitmap
import android.graphics.drawable.Drawable
import androidx.core.graphics.drawable.toBitmap
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import dagger.hilt.android.lifecycle.HiltViewModel
import nsu.krpo.academads.data.daos.advertisments.AdvertisementsDao
import nsu.krpo.academads.data.daos.saved_info.SavedRep
import nsu.krpo.academads.domain.model.ads.Category
import nsu.krpo.academads.ui.base.live_data.SingleLiveEvent
import nsu.krpo.academads.ui.base.live_data.update
import nsu.krpo.academads.ui.base.view.BaseViewModel
import nsu.krpo.academads.ui.create_ad.rv_photos.ItemPhotoWrapper
import java.io.ByteArrayOutputStream
import java.math.BigDecimal
import java.util.Objects
import javax.inject.Inject


@HiltViewModel
class CreateAdViewModel @Inject constructor(
    private val adsDao: AdvertisementsDao,
    private val savedRep: SavedRep
) : BaseViewModel() {

    private val _createAdEvent = SingleLiveEvent<String>()
    val createAdEvent: LiveData<String> = _createAdEvent


    private val _photo: MutableLiveData<List<ItemPhotoWrapper>> = MutableLiveData()
    val photo: LiveData<List<ItemPhotoWrapper>> = _photo

    private val _error: MutableLiveData<String> = MutableLiveData()
    val error: LiveData<String> = _error

    private val myPhotos: MutableList<ItemPhotoWrapper> = mutableListOf()

    private var myId = 1L

    init {
        //myId = savedRep.getSavedUserId()
    }

    fun addPhotoToList(photo: Drawable) {
        myPhotos.add(ItemPhotoWrapper(photo))
        _photo.value = myPhotos
    }

    fun onRemoveButtonClicked(position: Int) {
        myPhotos.removeAt(position)
        _photo.value = myPhotos
    }


    fun validateAd(title: String, priceText: String) =
        kotlin.runCatching {
            require(title.length >= 2) { SHORT_TITLE }
            require(title.length <= 40) { LONG_TITLE }
            require(priceText.any { it.isDigit() }) { PRICE_DIGIT }
        }

    fun createAd(
        title: String,
        description: String,
        priceText: String,
        category: Category,
        photo: Drawable?
    ) {
        adsDao.createAd(
            title,
            description,
            BigDecimal(priceText),
            category,
            myId
        )
            .setupDefaultSchedulers()
            .bindLoading()
            .subscribe(
                ::onAdCreated
            ) { error ->
                _error.value = error.message
            }.unsubscribeOnCleared()
        if (photo != null) {
            adsDao.addPhotoAd(bitmap2Bytes(photo.toBitmap(photo.intrinsicWidth, photo.intrinsicHeight)))
        }
    }

    private fun onAdCreated() {
        _createAdEvent.update { "Объявление отправлено на модерацию" }
    }

    fun bitmap2Bytes(bm: Bitmap): ByteArray {
        val baos = ByteArrayOutputStream()
        bm.compress(Bitmap.CompressFormat.PNG, 100, baos)
        return baos.toByteArray()
    }

    companion object ValidationErrors {
        const val SHORT_TITLE = "Заголовок должен содержать по крайней мере 2 символа"
        const val LONG_TITLE = "Заголовок может содержать не более 40 символов"
        const val PRICE_DIGIT = "Цена должна содержать только цифры"
    }
}