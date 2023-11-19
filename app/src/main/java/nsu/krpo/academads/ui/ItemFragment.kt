package nsu.krpo.academads.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import me.relex.circleindicator.CircleIndicator
import nsu.krpo.academads.R
import nsu.krpo.academads.databinding.FragmentItemBinding
import nsu.krpo.academads.domen.ImagesModel
import nsu.krpo.academads.ui.image_slider.ImageSliderAdapter

class ItemFragment : Fragment() {
    private lateinit var binding: FragmentItemBinding
    private var imagesModel: ImagesModel? = null
    lateinit var viewPagerAdapter: ImageSliderAdapter
    lateinit var indicator: CircleIndicator

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentItemBinding.inflate(inflater, container, false)
        imagesModel = ImagesModel()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        imagesModel?.images?.let {
            viewPagerAdapter = ImageSliderAdapter(requireContext(), it)
            binding.viewPager.adapter = viewPagerAdapter
            binding.viewPager.currentItem = 0
            indicator = requireView().findViewById(R.id.indicator) as CircleIndicator
            indicator.setViewPager(binding.viewPager)
        }
    }
}