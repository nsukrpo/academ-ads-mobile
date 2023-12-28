package nsu.krpo.academads.ui.my_likes.rv

import androidx.recyclerview.widget.RecyclerView.ViewHolder
import nsu.krpo.academads.databinding.ItemLikedBinding
import nsu.krpo.academads.domain.model.ads.Advertisement
import java.time.ZoneId

class LikedViewHolder (
    private val binding: ItemLikedBinding,
) : ViewHolder(binding.root) {

    fun bind(ad: LikedAdWrapper, onItemClicked: (Advertisement) -> Unit, onItemDisliked: (Advertisement) -> Unit) {
        binding.run {
            productItem.text = ad.ad.header
            date.text = ad.ad.publicationDate.atZone(ZoneId.systemDefault()).toLocalDate().toString()
            price.text = ad.ad.price.toPlainString()
            image.setImageDrawable(ad.cover)
            dislike.setOnClickListener { onItemDisliked(ad.ad) }
            root.setOnClickListener { onItemClicked(ad.ad) }
        }
    }
}