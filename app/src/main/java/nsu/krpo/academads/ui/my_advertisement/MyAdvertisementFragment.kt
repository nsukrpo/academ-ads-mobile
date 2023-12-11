package nsu.krpo.academads.ui.my_advertisement

import android.graphics.BitmapFactory
import android.graphics.drawable.BitmapDrawable
import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import dagger.hilt.android.AndroidEntryPoint
import nsu.krpo.academads.R
import nsu.krpo.academads.databinding.FragmentMyAdBinding
import nsu.krpo.academads.domain.model.ads.Advertisement
import nsu.krpo.academads.domain.model.ads.AdvertisementStatus
import nsu.krpo.academads.domain.model.ads.title
import nsu.krpo.academads.ui.advertisement.AdvertisementFragment
import nsu.krpo.academads.ui.base.view.BaseFragment
import nsu.krpo.academads.ui.base.view.viewBinding
import nsu.krpo.academads.ui.profile.ProfileScreenRoutes
import java.text.DateFormat
import java.text.SimpleDateFormat

@AndroidEntryPoint
class MyAdvertisementFragment : BaseFragment() {

    override val binding by viewBinding { inflater, container ->
        FragmentMyAdBinding.inflate(
            inflater,
            container,
            false
        )
    }
    override val viewModel: MyAdvertisementViewModel by viewModels()

    private lateinit var ad: Advertisement

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        ad = this.requireArguments().getParcelable(ARGS_KEY)!!
        setupView(ad)
        setupViewListeners()
        setupVmObservers()
    }

    private fun setupView(ad: Advertisement) {
        binding.run {
            productTitle.text = ad.header
            date.text = SimpleDateFormat("dd.MM.yyyy").format(ad.publicationDate)
            price.text = ad.price.toPlainString()
            status.text = ad.status.title()
            watches.text = "Просмотров: %s".format(ad.countWatch)
            image.setImageDrawable(
                BitmapDrawable(
                    BitmapFactory.decodeByteArray(
                        ad.photos[0].photo,
                        0,
                        ad.photos[0].photo.size
                    )
                )
            )
            if ((ad.status != AdvertisementStatus.GRANTED) && (ad.status != AdvertisementStatus.BOOKED)) {
                binding.soldButton.isEnabled = false
                binding.deleteButton.isEnabled = false
            }
        }
    }

    private fun setupViewListeners() {
        binding.soldButton.setOnClickListener {
            viewModel.changeAdToSold(ad)
            binding.status.text = AdvertisementStatus.SOLD.title()
            binding.soldButton.isEnabled = false
            binding.deleteButton.isEnabled = false
        }

        binding.deleteButton.setOnClickListener {
            viewModel.changeAdToDeleted(ad)
            binding.status.text = AdvertisementStatus.WITHDRAWN_FROM_SALE.title()
            binding.soldButton.isEnabled = false
            binding.deleteButton.isEnabled = false
        }

        binding.editButton.setOnClickListener {
            viewModel.onEdit(ad)
        }
    }

    private fun setupVmObservers() {
        viewModel.navEvent.observe(viewLifecycleOwner, ::obtainNavEvent)
    }

    private fun obtainNavEvent(direction: MyAdvertisementScreenRoutes) = when (direction) {
        is MyAdvertisementScreenRoutes.ToEditAd -> {
            //TODO findNavController().navigate(R.id.ToMyAds)
        }
    }

    companion object {

        const val ARGS_KEY = "MyAdvertisementFragment"
    }
}