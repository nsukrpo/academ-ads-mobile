package nsu.krpo.academads.ui.profile.purchases_rv

import androidx.recyclerview.widget.RecyclerView
import nsu.krpo.academads.databinding.ItemPurchaseMiniBinding
import nsu.krpo.academads.domain.model.ads.Advertisement
import java.text.SimpleDateFormat

class PurchasesViewHolder(
    private val binding: ItemPurchaseMiniBinding
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(purchase: PurchaseWrapper, onItemClicked: (Advertisement) -> Unit) =
        binding.run {
            image.setImageDrawable(purchase.cover)
            titleText.text = purchase.purchase.ads.header
            date.text = SimpleDateFormat("dd.MM.yyyy").format(purchase.purchase.date)
            price.text = purchase.purchase.price.toPlainString()
            binding.root.setOnClickListener { onItemClicked(purchase.purchase.ads) }
        }
}