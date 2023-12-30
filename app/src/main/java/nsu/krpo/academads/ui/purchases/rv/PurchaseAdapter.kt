package nsu.krpo.academads.ui.purchases.rv

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView.Adapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import nsu.krpo.academads.databinding.ItemPurchaseBinding
import nsu.krpo.academads.domain.model.ads.Advertisement

class PurchaseAdapter(
    private val onItemClicked: (Advertisement) -> Unit,
) : Adapter<ViewHolder>() {

    var items: List<PurchaseWrapper> = emptyList()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return PurchasesVH(
            ItemPurchaseBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        (holder as PurchasesVH).bind(items[position], onItemClicked)
    }


    override fun getItemCount(): Int = items.count()
}