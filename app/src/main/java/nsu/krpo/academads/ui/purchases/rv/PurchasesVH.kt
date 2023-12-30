package nsu.krpo.academads.ui.purchases.rv

import androidx.recyclerview.widget.RecyclerView.ViewHolder
import nsu.krpo.academads.databinding.ItemPurchaseBinding
import nsu.krpo.academads.domain.model.ads.Advertisement
import java.time.ZoneId

class PurchasesVH(
    private val binding: ItemPurchaseBinding
): ViewHolder(binding.root) {

    fun bind(ad: PurchaseWrapper, onItemClicked: (Advertisement) -> Unit) {
        binding.run {
            title.text = ad.ad.ads.header
            date.text = ad.ad.ads.publicationDate.atZone(ZoneId.systemDefault()).toLocalDate().toString()
            price.text = ad.ad.price.toPlainString()
            image.setImageDrawable(ad.cover)
            root.setOnClickListener { onItemClicked(ad.ad.ads) }
        }
    }
}