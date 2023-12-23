package nsu.krpo.academads.ui.category.rv

import androidx.recyclerview.widget.RecyclerView.ViewHolder
import nsu.krpo.academads.databinding.ItemProductBinding
import nsu.krpo.academads.domain.model.ads.Advertisement
import java.text.SimpleDateFormat

class AdViewHolder(
    private val binding: ItemProductBinding,
) : ViewHolder(binding.root){
    fun bind(ad: AdvertisementWrapper, onItemClicked: (Advertisement) -> Unit) =
        binding.run {
            productItem.text = ad.ad.header
            date.text = SimpleDateFormat("dd.mm.yyyy").format(ad.ad.publicationDate)
            price.text = ad.ad.price.toPlainString()
            image.setImageDrawable(ad.cover)
            root.setOnClickListener { onItemClicked(ad.ad) }
        }
}