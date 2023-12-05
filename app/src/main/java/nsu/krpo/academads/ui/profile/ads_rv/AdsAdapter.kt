package nsu.krpo.academads.ui.profile.ads_rv

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import nsu.krpo.academads.databinding.ItemProductMiniBinding
import nsu.krpo.academads.domain.model.ads.Advertisement

class AdsAdapter(
    private val onItemClicked: (Advertisement) -> Unit,
) : RecyclerView.Adapter<AdsViewHolder>() {

    var items: List<AdvertismentWrapper> = emptyList()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AdsViewHolder =
        AdsViewHolder(
            ItemProductMiniBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )

    override fun onBindViewHolder(holder: AdsViewHolder, position: Int) {
        holder.bind(
            items[position],
            onItemClicked
        )
    }

    override fun getItemCount(): Int = items.size
}