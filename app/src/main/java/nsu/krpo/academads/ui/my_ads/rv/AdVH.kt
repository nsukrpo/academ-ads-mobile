package nsu.krpo.academads.ui.my_ads.rv

import androidx.recyclerview.widget.RecyclerView.ViewHolder
import nsu.krpo.academads.databinding.ItemMyAdBinding
import nsu.krpo.academads.domain.model.ads.Advertisement
import nsu.krpo.academads.domain.model.ads.title
import java.text.SimpleDateFormat

class AdVH(private val binding: ItemMyAdBinding) : ViewHolder(binding.root) {
    fun bind(
        ad: AdWrapper,
        onItemClicked: (Advertisement) -> Unit,
    ) {
        binding.run {
            title.text = ad.ad.header
            date.text = SimpleDateFormat("dd.mm.yyyy").format(ad.ad.publicationDate)
            price.text = ad.ad.price.toPlainString()
            image.setImageDrawable(ad.cover)
            status.text = ad.ad.status.title()
            root.setOnClickListener { onItemClicked(ad.ad) }
        }


    }
}