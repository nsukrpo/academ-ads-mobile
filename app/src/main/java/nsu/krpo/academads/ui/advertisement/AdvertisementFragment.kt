package nsu.krpo.academads.ui.advertisement

import android.graphics.BitmapFactory
import android.graphics.drawable.BitmapDrawable
import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import dagger.hilt.android.AndroidEntryPoint
import nsu.krpo.academads.databinding.FragmentItemBinding
import nsu.krpo.academads.domain.model.ads.Advertisement
import nsu.krpo.academads.domain.model.ads.title
import nsu.krpo.academads.ui.base.view.BaseFragment
import nsu.krpo.academads.ui.base.view.viewBinding
import java.text.SimpleDateFormat

@AndroidEntryPoint
class AdvertisementFragment : BaseFragment() {

    override val binding by viewBinding { inflater, container -> FragmentItemBinding.inflate(inflater, container, false) }

    override val viewModel: AdvertisementViewModel by viewModels()

    private lateinit var ad: Advertisement

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        ad = requireArguments().getParcelable(ARGS_KEY)!!

        setupView()
        setupViewListeners()
    }

    private fun setupView() {
        with(binding) {
            itemTitle.text = ad.header
            price.text = ad.price.toPlainString()
            date.text = SimpleDateFormat("dd.mm.yyyy").format(ad.publicationDate)
            chipCategory.text = ad.category.title()
            views.text = "%s просмотров".format(ad.countWatch)
            description.text = ad.description
            sellerAvatar.setImageDrawable(BitmapDrawable(BitmapFactory.decodeByteArray(ad.author.avatar.photo, 0, ad.author.avatar.photo.size)))
            sellerName.text = ad.author.name
        }
    }

    private fun setupViewListeners() {

    }

    companion object {
        const val ARGS_KEY = "AdvertisementFragment"
    }
}