package nsu.krpo.academads.ui.bans.bans_rv

import androidx.recyclerview.widget.RecyclerView.ViewHolder
import nsu.krpo.academads.databinding.ItemBanBinding
import nsu.krpo.academads.domain.model.ads.Category
import nsu.krpo.academads.domain.model.ads.title
import nsu.krpo.academads.ui.categories.rv.CategoryWrapper
import java.text.SimpleDateFormat

class BansViewHolder(
    private val binding: ItemBanBinding
) : ViewHolder(binding.root) {
    fun bind(ban: BanWrapper) =
        binding.run {
            startTime.text = "Начало блокировки:\n %s".format(SimpleDateFormat("dd.mm.yyyy hh:mm").format(ban.startTime))
            endTime.text = "Конец блокировки:\n %s".format(SimpleDateFormat("dd.mm.yyyy hh:mm").format(ban.endTime))
            reason.text = "Причина:\n%s".format(ban.reason.title())
        }
}