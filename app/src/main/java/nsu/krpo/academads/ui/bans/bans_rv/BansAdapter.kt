package nsu.krpo.academads.ui.bans.bans_rv

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.Adapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import nsu.krpo.academads.databinding.ItemBanBinding
import nsu.krpo.academads.databinding.ItemCategoryBinding
import nsu.krpo.academads.ui.categories.rv.CategoryViewHolder
import nsu.krpo.academads.ui.categories.rv.CategoryWrapper

class BansAdapter: Adapter<ViewHolder>() {

    var items: List<BanWrapper> = emptyList()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        (holder as BansViewHolder).bind(items[position])
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
        BansViewHolder(
            ItemBanBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
}