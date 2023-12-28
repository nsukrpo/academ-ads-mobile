package nsu.krpo.academads.ui.create_ad.rv_photos

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