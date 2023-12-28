package nsu.krpo.academads.ui.advertisement

import android.graphics.BitmapFactory
import android.graphics.Color
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.view.View
import androidx.appcompat.content.res.AppCompatResources
import androidx.core.content.res.ResourcesCompat
import androidx.fragment.app.viewModels
import dagger.hilt.android.AndroidEntryPoint
import me.relex.circleindicator.CircleIndicator
import nsu.krpo.academads.R
import nsu.krpo.academads.databinding.FragmentItemBinding
import nsu.krpo.academads.domain.model.ads.Advertisement
import nsu.krpo.academads.domain.model.ads.AdvertisementStatus
import nsu.krpo.academads.domain.model.ads.ImagesModel
import nsu.krpo.academads.domain.model.ads.title
import nsu.krpo.academads.ui.base.view.BaseFragment
import nsu.krpo.academads.ui.base.view.viewBinding
import nsu.krpo.academads.ui.image_slider.ImageSliderAdapter
import java.text.SimpleDateFormat
import java.time.ZoneId

@AndroidEntryPoint
class AdvertisementFragment : BaseFragment() {

    override val binding by viewBinding { inflater, container -> FragmentItemBinding.inflate(inflater, container, false) }

    override val viewModel: AdvertisementViewModel by viewModels()

    private lateinit var ad: Advertisement
    private var isLiked: Boolean = false
    private var imagesModel: ImagesModel? = null
    private lateinit var viewPagerAdapter: ImageSliderAdapter
    private lateinit var indicator: CircleIndicator

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        ad = requireArguments().getParcelable(ARGS_KEY)!!

        setupView()
        setupViewListeners()
    }



    private fun setupView() {
        imagesModel = ImagesModel()
        imagesModel?.images?.let {
            viewPagerAdapter = ImageSliderAdapter(requireContext(), it)
            binding.viewPager.adapter = viewPagerAdapter
            binding.viewPager.currentItem = 0
            indicator = requireView().findViewById(R.id.indicator) as CircleIndicator
            indicator.setViewPager(binding.viewPager)
        }
        with(binding) {
            itemTitle.text = ad.header
            price.text = ad.price.toPlainString()
            date.text = ad.publicationDate.atZone(ZoneId.systemDefault()).toLocalDate().toString()
            chipCategory.text = ad.category.title()
            views.text = "%s просмотров".format(ad.countWatch)
            description.text = ad.description
            sellerAvatar.setImageDrawable(BitmapDrawable(BitmapFactory.decodeByteArray(ad.author.avatar.photo, 0, ad.author.avatar.photo.size)))
            sellerName.text = ad.author.name
        }
        if (ad.status != AdvertisementStatus.GRANTED) {
            binding.bookButton.isEnabled = false
        }
    }

    private fun setupViewListeners() {
        binding.bookButton.setOnClickListener {
            viewModel.bookAd(ad)
        }
        binding.like.setOnClickListener {
            isLiked = !isLiked
            if (isLiked) {
                binding.like.icon = AppCompatResources.getDrawable(requireContext(), R.drawable.heart_red)
            } else {
                binding.like.icon = AppCompatResources.getDrawable(requireContext(), R.drawable.heart)
            }
            viewModel.likeAd(ad)
        }

        binding.subscribeButton.setOnClickListener {
            viewModel.subscribe(ad.author)
        }
    }

    companion object {

        const val ARGS_KEY = "AdvertisementFragment"
    }
}