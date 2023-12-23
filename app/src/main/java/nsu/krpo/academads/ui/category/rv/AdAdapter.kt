package nsu.krpo.academads.ui.category.rv

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import nsu.krpo.academads.databinding.ItemCategoryBinding
import nsu.krpo.academads.databinding.ItemProductBinding
import nsu.krpo.academads.domain.model.ads.Advertisement
import nsu.krpo.academads.ui.categories.rv.CategoryViewHolder
import nsu.krpo.academads.ui.categories.rv.CategoryWrapper

class AdAdapter (
    private val onItemClicked: (Advertisement) -> Unit,
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    var items: List<AdvertisementWrapper> = emptyList()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as AdViewHolder).bind(items[position], onItemClicked)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder =
        AdViewHolder(
            ItemProductBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            ),
        )
}