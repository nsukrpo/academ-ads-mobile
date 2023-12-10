package nsu.krpo.academads.ui.create_ad.photos_rv

import androidx.recyclerview.widget.RecyclerView.ViewHolder
import nsu.krpo.academads.databinding.ItemPhotoBinding

class PhotoViewHolder(
    private val binding: ItemPhotoBinding
) : ViewHolder(binding.root) {

    fun bind(photo: ItemPhotoWrapper, position: Int, onButtonClicked: (Int) -> Unit) {
        binding.image.setImageDrawable(photo.photo)
        binding.removeButton.setOnClickListener { onButtonClicked(position) }
    }
}