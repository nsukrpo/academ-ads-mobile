package nsu.krpo.academads.ui.profile.ads_rv

import androidx.recyclerview.widget.RecyclerView
import nsu.krpo.academads.databinding.ItemProductMiniBinding
import nsu.krpo.academads.domain.model.ads.Advertisement
import nsu.krpo.academads.domain.model.ads.title

class AdsViewHolder(
    private val binding: ItemProductMiniBinding,
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(ad: AdvertismentWrapper, onItemClicked: (Advertisement) -> Unit) =
        binding.run {
            image.setImageDrawable(ad.cover)
            titleText.text = ad.title
            status.text = ad.status.title()
        }
}