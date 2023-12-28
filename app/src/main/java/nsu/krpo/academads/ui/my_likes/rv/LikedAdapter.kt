package nsu.krpo.academads.ui.my_likes.rv

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView.Adapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import nsu.krpo.academads.databinding.ItemLikedBinding
import nsu.krpo.academads.domain.model.ads.Advertisement

class LikedAdapter (
    private val onItemClicked: (Advertisement) -> Unit,
    private val onItemDisliked: (Advertisement) -> Unit,
) : Adapter<ViewHolder>() {
    var items: List<LikedAdWrapper> = emptyList()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun getItemCount(): Int = items.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return LikedViewHolder(
            ItemLikedBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        (holder as LikedViewHolder).bind(items[position], onItemClicked , onItemDisliked)
    }
}