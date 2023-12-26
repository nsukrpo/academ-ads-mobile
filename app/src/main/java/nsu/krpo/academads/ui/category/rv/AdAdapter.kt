package nsu.krpo.academads.ui.category.rv

import android.graphics.drawable.Drawable
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.appcompat.content.res.AppCompatResources
import androidx.recyclerview.widget.RecyclerView
import nsu.krpo.academads.R
import nsu.krpo.academads.databinding.ItemCategoryBinding
import nsu.krpo.academads.databinding.ItemProductBinding
import nsu.krpo.academads.domain.model.ads.Advertisement
import nsu.krpo.academads.ui.categories.rv.CategoryViewHolder
import nsu.krpo.academads.ui.categories.rv.CategoryWrapper

class AdAdapter (
    private val onItemClicked: (Advertisement) -> Unit,
    private val onItemLiked: (Advertisement) -> Unit,
    private val onItemDisliked: (Advertisement) -> Unit,
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    var items: List<AdvertisementWrapper> = emptyList()
        set(value) {
            field = value
            notifyDataSetChanged()
        }
    private var heartRed: Drawable? = null
    private var heart: Drawable? = null

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as AdViewHolder).bind(items[position], onItemClicked, onItemLiked, onItemDisliked)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        heartRed = AppCompatResources.getDrawable(parent.context, R.drawable.heart_red)
        heart = AppCompatResources.getDrawable(parent.context, R.drawable.heart)
        return AdViewHolder(
            ItemProductBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            ),
            heart,
            heartRed
        )
    }
}