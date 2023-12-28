package nsu.krpo.academads.ui.category.rv

import android.graphics.drawable.Drawable
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import nsu.krpo.academads.databinding.ItemProductBinding
import nsu.krpo.academads.domain.model.ads.Advertisement
import java.text.SimpleDateFormat
import java.time.ZoneId

class AdViewHolder(
    private val binding: ItemProductBinding,
    private val heart: Drawable?,
    private val redHeart: Drawable?,
    private var isLiked: Boolean = false,
) : ViewHolder(binding.root){
    private lateinit var onLike: (Advertisement) -> Unit
    private lateinit var onLDislike: (Advertisement) -> Unit
    private lateinit var bindedAd: Advertisement

    fun bind(ad: AdvertisementWrapper, onItemClicked: (Advertisement) -> Unit, onItemLiked: (Advertisement) -> Unit, onItemDisliked: (Advertisement) -> Unit) =
        binding.run {
            productItem.text = ad.ad.header
            date.text = ad.ad.publicationDate.atZone(ZoneId.systemDefault()).toLocalDate().toString()
            price.text = ad.ad.price.toPlainString()
            image.setImageDrawable(ad.cover)
            onLike = onItemLiked
            onLDislike = onItemDisliked
            bindedAd = ad.ad
            like.setOnClickListener { onLikeClickListener() }
            root.setOnClickListener { onItemClicked(ad.ad) }
        }

    private fun onLikeClickListener() {
        isLiked = !isLiked
        if (isLiked) {
            binding.like.icon = redHeart
            onLike(bindedAd)
        } else {
            binding.like.icon = heart
            onLDislike(bindedAd)
        }
    }
}