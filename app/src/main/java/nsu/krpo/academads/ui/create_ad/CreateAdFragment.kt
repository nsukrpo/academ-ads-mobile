package nsu.krpo.academads.ui.create_ad

import android.content.Intent
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import dagger.hilt.android.AndroidEntryPoint
import nsu.krpo.academads.databinding.FragmentCreateAdBinding
import nsu.krpo.academads.domain.model.ads.Category
import nsu.krpo.academads.ui.base.view.BaseFragment
import nsu.krpo.academads.ui.base.view.viewBinding
import nsu.krpo.academads.ui.create_ad.photos_rv.PhotoAdapter

@AndroidEntryPoint
class CreateAdFragment : BaseFragment() {

    override val viewModel: CreateAdViewModel by viewModels()
    override val binding by viewBinding { inflater, container ->
        FragmentCreateAdBinding.inflate(inflater, container, false)
    }

    private val adapter: PhotoAdapter by lazy {
        PhotoAdapter(viewModel::onRemoveButtonClicked)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.rvPhotos.adapter = adapter
        setupViewListeners()
        setupVmObservers()
    }

    private fun setupViewListeners() {
        setupSwitchListener()
        setupPhotoButtonListener()
        setupCreateAdButtonListener()
    }

    private fun setupVmObservers() {
        viewModel.createAdEvent.observe(
            viewLifecycleOwner
        ) { str ->
            Toast.makeText(context, str, Toast.LENGTH_LONG).show()
            findNavController().popBackStack()
        }
        viewModel.photo.observe(
            viewLifecycleOwner
        ) { photos ->
            adapter.items = photos
        }
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

    private fun setupCreateAdButtonListener() {

        binding.createAdButton.setOnClickListener {
            val category =
                when (binding.chipsCategory.checkedChipId) {
                    binding.chipEdServices.id -> Category.EDUCATIONAL_SERVICE
                    binding.chipEdServices.id -> Category.EDUCATIONAL_SERVICE
                    binding.chipEdSupplies.id -> Category.EDUCATIONAL_SUPPLIES
                    binding.chipAppliances.id -> Category.APPLIANCES
                    binding.chipElectronics.id -> Category.ELECTRONICS
                    else -> Category.OTHER
                }

            var price = binding.priceEt.text.toString()
            if (binding.forFreeSwitch.isChecked) {
                price = "0"
            }

            val dataValidation = viewModel.validateAd(
                title = binding.titleEt.text.toString(),
                priceText = price,
            )

            dataValidation.onSuccess {
                viewModel.createAd(
                    title = binding.titleEt.text.toString(),
                    description = binding.descriptionEt.text.toString(),
                    priceText = price,
                    category = category
                )
            }

            dataValidation.onFailure {exception ->
                run {
                    binding.errorTv.text =exception.message
                }
            }
        }
    }

    @Deprecated("Deprecated in Java")
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
}