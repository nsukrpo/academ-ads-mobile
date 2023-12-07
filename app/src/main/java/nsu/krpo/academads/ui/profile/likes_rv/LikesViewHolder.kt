package nsu.krpo.academads.ui.profile.likes_rv

import androidx.recyclerview.widget.RecyclerView
import nsu.krpo.academads.databinding.ItemLikedMiniBinding
import nsu.krpo.academads.domain.model.ads.Advertisement


class LikesViewHolder(private val binding: ItemLikedMiniBinding) : RecyclerView.ViewHolder(binding.root) {

    fun bind(likedWrapper: LikedWrapper, onItemClicked: (Advertisement) -> Unit) =
        binding.run {
            image.setImageDrawable(likedWrapper.cover)
            titleText.text = likedWrapper.title
            price.text = likedWrapper.price.toPlainString()
        }
}