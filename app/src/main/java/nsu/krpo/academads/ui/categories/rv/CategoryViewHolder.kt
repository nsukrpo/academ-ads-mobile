package nsu.krpo.academads.ui.categories.rv

import androidx.recyclerview.widget.RecyclerView
import nsu.krpo.academads.databinding.ItemCategoryBinding
import nsu.krpo.academads.domain.model.ads.Category
import nsu.krpo.academads.domain.model.ads.title


class CategoryViewHolder(
    private val binding: ItemCategoryBinding
) : RecyclerView.ViewHolder(binding.root) {

        fun bind(category: CategoryWrapper, onItemClicked: (Category) -> Unit) =
            binding.run {
                categoryText.text = category.category.title()
                countText.text = category.itemsCount.toString()
                image.setImageDrawable(category.image)
                root.setOnClickListener { onItemClicked(category.category) }
            }

}