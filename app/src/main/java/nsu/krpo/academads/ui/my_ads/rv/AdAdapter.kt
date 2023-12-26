package nsu.krpo.academads.ui.my_ads.rv

import android.graphics.drawable.Drawable
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.appcompat.content.res.AppCompatResources
import androidx.recyclerview.widget.RecyclerView
import nsu.krpo.academads.R
import nsu.krpo.academads.databinding.ItemMyAdBinding
import nsu.krpo.academads.databinding.ItemProductBinding
import nsu.krpo.academads.domain.model.ads.Advertisement

class AdAdapter(
    private val onItemClicked: (Advertisement) -> Unit,
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    var items: List<AdWrapper> = emptyList()
        set(value) {
            field = value
            notifyDataSetChanged()
        }
    private var heartRed: Drawable? = null
    private var heart: Drawable? = null

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as AdVH).bind(items[position], onItemClicked)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {

        return AdVH(
            ItemMyAdBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            ),
        )
    }


}