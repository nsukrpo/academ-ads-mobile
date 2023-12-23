package nsu.krpo.academads.ui.edit_item.photos_rv

import androidx.recyclerview.widget.RecyclerView
import nsu.krpo.academads.databinding.ItemPhotoBinding

class PhotoViewHolder(
    private val binding: ItemPhotoBinding
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(photo: ItemPhotoWrapper, position: Int, onButtonClicked: (Int) -> Unit) {
        binding.image.setImageDrawable(photo.photo)
        binding.removeButton.setOnClickListener { onButtonClicked(position) }
    }
}