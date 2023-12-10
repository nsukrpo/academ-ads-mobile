package nsu.krpo.academads.ui.create_ad

import android.graphics.drawable.Drawable
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import dagger.hilt.android.lifecycle.HiltViewModel
import nsu.krpo.academads.ui.base.view.BaseViewModel
import nsu.krpo.academads.ui.create_ad.photos_rv.ItemPhotoWrapper
import javax.inject.Inject

@HiltViewModel
class CreateAdViewModel @Inject constructor() : BaseViewModel() {

    private val _photo: MutableLiveData<List<ItemPhotoWrapper>> = MutableLiveData()
    val photo: LiveData<List<ItemPhotoWrapper>> = _photo

    private val myPhotos: MutableList<ItemPhotoWrapper> = mutableListOf()

    fun addPhotoToList(photo: Drawable) {
        myPhotos.add(ItemPhotoWrapper(photo))
        _photo.value = myPhotos
    }

    fun onRemoveButtonClicked(position: Int) {
        myPhotos.removeAt(position)
        _photo.value = myPhotos
    }
}