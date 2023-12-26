package nsu.krpo.academads.ui.profile.likes_rv

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import nsu.krpo.academads.databinding.ItemLikedMiniBinding
import nsu.krpo.academads.domain.model.ads.Advertisement

class LikedAdapter(
    private val onItemClicked: (Advertisement) -> Unit
) : RecyclerView.Adapter<LikesViewHolder>() {

    var items: List<LikedWrapper> = emptyList()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LikesViewHolder =
        LikesViewHolder(
            ItemLikedMiniBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )

    override fun onBindViewHolder(holder: LikesViewHolder, position: Int) {
        holder.bind(items[position], onItemClicked)
    }

    override fun getItemCount(): Int = items.size
}