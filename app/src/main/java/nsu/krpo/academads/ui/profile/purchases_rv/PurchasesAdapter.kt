package nsu.krpo.academads.ui.profile.purchases_rv

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import nsu.krpo.academads.databinding.ItemPurchaseMiniBinding
import nsu.krpo.academads.domain.model.ads.Purchase

class PurchasesAdapter(
    private val onItemClicked: (Purchase) -> Unit
) : RecyclerView.Adapter<PurchasesViewHolder>() {

    var items: List<PurchaseWrapper> = emptyList()
        set(value) {
            field = value
            notifyDataSetChanged()
        }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PurchasesViewHolder =
        PurchasesViewHolder(ItemPurchaseMiniBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        ))

    override fun onBindViewHolder(holder: PurchasesViewHolder, position: Int) {
        holder.bind(
            items[position],
            onItemClicked
        )
    }

    override fun getItemCount(): Int = items.size
}