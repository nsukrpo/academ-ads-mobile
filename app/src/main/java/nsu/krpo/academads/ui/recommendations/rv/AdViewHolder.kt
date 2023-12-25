package nsu.krpo.academads.ui.recommendations.rv

import android.graphics.drawable.Drawable
import android.util.Log
import androidx.recyclerview.widget.RecyclerView
import nsu.krpo.academads.databinding.ItemProductBinding
import nsu.krpo.academads.domain.model.ads.Advertisement
import java.text.SimpleDateFormat

class AdViewHolder(
    private val binding: ItemProductBinding,
    private val heart: Drawable?,
    private val redHeart: Drawable?,
    private var isLiked: Boolean = false,
) : RecyclerView.ViewHolder(binding.root){
    private lateinit var onLike: (Advertisement) -> Unit
    private lateinit var onLDislike: (Advertisement) -> Unit
    private lateinit var bindedAd: Advertisement

    fun bind(ad: AdvertisementWrapper, onItemClicked: (Advertisement) -> Unit, onItemLiked: (Advertisement) -> Unit, onItemDisliked: (Advertisement) -> Unit) =
        binding.run {
            productItem.text = ad.ad.header
            date.text = SimpleDateFormat("dd.mm.yyyy").format(ad.ad.publicationDate)
            price.text = ad.ad.price.toPlainString()
            image.setImageDrawable(ad.cover)
            onLike = onItemLiked
            onLDislike = onItemDisliked
            bindedAd = ad.ad
            like.setOnClickListener { onLikeClickListener() }
            root.setOnClickListener { onItemClicked(ad.ad) }
            Log.i("MINE", "binded")
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