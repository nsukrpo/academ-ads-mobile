package nsu.krpo.academads.ui.create_ad

import android.content.ContentResolver
import android.content.Intent
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import dagger.hilt.android.AndroidEntryPoint
import nsu.krpo.academads.R
import nsu.krpo.academads.databinding.FragmentCreateAdBinding
import nsu.krpo.academads.ui.base.view.BaseFragment
import nsu.krpo.academads.ui.base.view.viewBinding
import nsu.krpo.academads.ui.create_ad.photos_rv.ItemPhotoWrapper
import nsu.krpo.academads.ui.create_ad.photos_rv.PhotoAdapter
import java.io.InputStream

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
    }

    private fun setupVmObservers() {
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