package nsu.krpo.academads.ui.categories.rv

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import nsu.krpo.academads.databinding.ItemCategoryBinding
import nsu.krpo.academads.domain.model.ads.Category

class CategoriesAdapter(
    private val onItemClicked: (Category) -> Unit,
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    var items: List<CategoryWrapper> = emptyList()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as CategoryViewHolder).bind(items[position], onItemClicked)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder =
        CategoryViewHolder(
            ItemCategoryBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
}