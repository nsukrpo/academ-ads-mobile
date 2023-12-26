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
import nsu.krpo.academads.ui.edit_item.EditFragment
import java.text.SimpleDateFormat

@AndroidEntryPoint
class MyAdvertisementFragment : BaseFragment() {

    override val binding by viewBinding { inflater, container -> FragmentMyAdBinding.inflate(inflater, container, false) }
    override val viewModel: MyAdvertisementViewModel by viewModels()

    private lateinit var ad: Advertisement


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        ad = requireArguments().getParcelable(ARGS_KEY)!!

        setupVmObservers()
        setupView()
        setupViewListeners()
    }

    private fun setupVmObservers() {
        viewModel.run {
            navEvent.observe(viewLifecycleOwner, ::obtainNavEvent)
        }
    }

    private fun setupView() {
        binding.soldButton.isEnabled = (ad.status == AdvertisementStatus.GRANTED) || (ad.status == AdvertisementStatus.BOOKED)
        binding.deleteButton.isEnabled = (ad.status == AdvertisementStatus.GRANTED) || (ad.status == AdvertisementStatus.BOOKED)
        binding.editButton.isEnabled = (ad.status == AdvertisementStatus.GRANTED) || (ad.status == AdvertisementStatus.BOOKED)



        with(binding) {
            productItem.text = ad.header
            date.text = SimpleDateFormat("dd.mm.yyyy").format(ad.publicationDate)
            price.text = ad.price.toPlainString()
            watches.text = "Просмотров: %s".format(ad.countWatch)
            status.text = ad.status.title()
            image.setImageDrawable(ad.photos[0].photo)
        }
    }

    private fun setupViewListeners() {
        binding.soldButton.setOnClickListener {
            viewModel.sellAd(ad)
            binding.soldButton.isEnabled = false
        }

        binding.deleteButton.setOnClickListener {
            viewModel.deleteAd(ad)
            binding.deleteButton.isEnabled = false
        }

        binding.editButton.setOnClickListener {
            viewModel.onEditAd(ad)
        }
    }

    private fun obtainNavEvent(direction: MyAdvertisementScreenRoutes) = when(direction) {
        is MyAdvertisementScreenRoutes.ToEditAd -> {
            findNavController().navigate(R.id.ToEdit, Bundle().apply {
                putParcelable(EditFragment.ARGS_KEY, direction.ad)
            })
        }
    }




    companion object {

        const val ARGS_KEY = "MyAdvertisementFragment"
    }
}