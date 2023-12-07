package nsu.krpo.academads.ui.profile.purchases_rv

import androidx.recyclerview.widget.RecyclerView
import nsu.krpo.academads.databinding.ItemPurchaseMiniBinding
import nsu.krpo.academads.domain.model.ads.Purchase
import java.text.SimpleDateFormat

class PurchasesViewHolder(
    private val binding: ItemPurchaseMiniBinding
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(purchase: PurchaseWrapper, onItemClicked: (Purchase) -> Unit) =
        binding.run {
            image.setImageDrawable(purchase.cover)
            titleText.text = purchase.ad.header
            date.text = SimpleDateFormat("dd.MM.yyyy").format(purchase.date)
            price.text = purchase.price.toPlainString()
        }
}