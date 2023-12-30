package nsu.krpo.academads.ui.edit_item

import android.content.Intent
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import dagger.hilt.android.AndroidEntryPoint
import nsu.krpo.academads.R
import nsu.krpo.academads.databinding.FragmentEditBinding
import nsu.krpo.academads.domain.model.ads.Advertisement
import nsu.krpo.academads.domain.model.ads.AdvertisementStatus
import nsu.krpo.academads.domain.model.ads.Category
import nsu.krpo.academads.ui.base.view.BaseFragment
import nsu.krpo.academads.ui.base.view.viewBinding
import nsu.krpo.academads.ui.edit_item.photos_rv.ItemPhotoWrapper
import nsu.krpo.academads.ui.edit_item.photos_rv.PhotoAdapter
import nsu.krpo.academads.ui.my_advertisement.MyAdvertisementFragment
import java.math.BigDecimal

@AndroidEntryPoint
class EditFragment : BaseFragment() {

    override val binding by viewBinding { inflater, container ->
        FragmentEditBinding.inflate(inflater, container, false)
    }
    override val viewModel: EditViewModel by viewModels()

    private val adapter: PhotoAdapter by lazy {
        PhotoAdapter(viewModel::onRemoveButtonClicked)
    }

    private lateinit var ad: Advertisement

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        ad = requireArguments().getParcelable(ARGS_KEY)!!
        binding.rvPhotos.adapter = adapter
        viewModel.providePhotos(ad.photos.map {
            ItemPhotoWrapper(
                it.photo
            )
        })

        setupVmObservers()
        setupView()
        setupViewListeners()
    }

    private fun setupVmObservers() {
        viewModel.photo.observe(
            viewLifecycleOwner
        ) { photos ->
            adapter.items = photos
        }

        viewModel.navEvent.observe(
            viewLifecycleOwner,
            ::obtainNavEvent
        )
    }

    private fun setupView() {
        with(binding) {
            titleEt.setText(ad.header)
            descriptionEt.setText(ad.description)
            if (ad.price.toInt() != 0) {
                priceEt.setText(ad.price.toPlainString())
                priceLayout.isEnabled = true
                forFreeSwitch.isChecked = false
            } else {
                priceLayout.isEnabled = false
                forFreeSwitch.isChecked = true
            }
            when (ad.category) {
                Category.EDUCATIONAL_STUFF -> binding.edSupplies.isChecked = true
                Category.HOUSEHOLD_APPLIANCE -> binding.appliances.isChecked = true
                Category.DEVICES -> binding.electronics.isChecked = true
                Category.STUDY_SERVICE -> binding.edServices.isChecked = true
                Category.OTHER -> binding.other.isChecked = true
            }
        }
        adapter.items = ad.photos.map {
            ItemPhotoWrapper(
                it.photo
            )
        }
    }

    private fun setupViewListeners() {
        setupSwitchListener()
        setupPhotoButtonListener()
        setupSendToModerationListener()
    }

    private fun setupSwitchListener() {
        binding.forFreeSwitch.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                with(binding.priceEt) {
                    isEnabled = false
                    alpha = 0.5F
                    setText("")
                }
                binding.priceLayout.alpha = 0.5F
            } else {
                binding.priceEt.isEnabled = true
                binding.priceEt.alpha = 1F
                binding.priceLayout.alpha = 1F
            }
        }
    }

    private fun setupPhotoButtonListener() {
        binding.pinPhotoButton.setOnClickListener {
            startActivityForResult(
                Intent(
                    Intent.ACTION_PICK,
                    android.provider.MediaStore.Images.Media.INTERNAL_CONTENT_URI
                ),
                3
            )
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == -1) {
            if (requestCode == 3) {
                val ins = activity?.contentResolver?.openInputStream(data!!.data!!)
                viewModel.addPhotoToList(
                    Drawable.createFromStream(
                        ins,
                        data!!.data.toString()
                    )!!
                )
                ins?.close()
            }
        }
    }

    private fun setupSendToModerationListener() {

        binding.moderationButton.setOnClickListener {
            var price = BigDecimal(0)
            if (!binding.forFreeSwitch.isChecked) {
                price = BigDecimal(binding.priceEt.text.toString())
            }

            var category = ad.category
            if (binding.edServices.isChecked) {
                category = Category.STUDY_SERVICE
            }
            if (binding.edSupplies.isChecked) {
                category = Category.EDUCATIONAL_STUFF
            }
            if (binding.appliances.isChecked) {
                category = Category.HOUSEHOLD_APPLIANCE
            }
            if (binding.electronics.isChecked) {
                category = Category.DEVICES
            }
            if (binding.other.isChecked) {
                category = Category.OTHER
            }
            viewModel.onModeration(
                ad,
                binding.titleEt.text.toString(),
                binding.descriptionEt.text.toString(),
                price,
                category,
                AdvertisementStatus.SENT_MODERATION
            )
        }
    }

    private fun obtainNavEvent(direction: EditScreenRoutes) {
        when(direction) {
            is EditScreenRoutes.ToMyAd -> findNavController().navigate(R.id.BackToAd, Bundle().apply {
                putParcelable(MyAdvertisementFragment.ARGS_KEY, direction.ad)
            })
        }
    }


    companion object {

        const val ARGS_KEY = "EditFragment"
    }
}