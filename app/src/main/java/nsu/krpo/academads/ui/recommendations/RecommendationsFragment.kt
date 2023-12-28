package nsu.krpo.academads.ui.recommendations

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import dagger.hilt.android.AndroidEntryPoint
import nsu.krpo.academads.R
import nsu.krpo.academads.databinding.FragmentRecomendationsBinding
import nsu.krpo.academads.domain.model.ads.Advertisement
import nsu.krpo.academads.ui.advertisement.AdvertisementFragment
import nsu.krpo.academads.ui.base.view.BaseFragment
import nsu.krpo.academads.ui.base.view.viewBinding
import nsu.krpo.academads.ui.recommendations.rv.AdAdapter
import nsu.krpo.academads.ui.recommendations.rv.AdvertisementWrapper

@AndroidEntryPoint
class RecommendationsFragment : BaseFragment() {

    override val binding by viewBinding { inflater, container ->
        FragmentRecomendationsBinding.inflate(inflater, container, false)
    }
    override val viewModel: RecommendationsViewModel by viewModels()

    private val recommendationsAdapter by lazy {
        AdAdapter(
            viewModel::onItemClicked,
            viewModel::onItemLiked,
            viewModel::onItemDisliked
        )
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.rvAds.adapter = recommendationsAdapter
        Log.i("MINE", "onVC")
        setupVmObservers()
    }

    private fun setupVmObservers() {
        viewModel.navEvent.observe(viewLifecycleOwner, ::obtainNavEvent)
        viewModel.ads.observe(viewLifecycleOwner, ::onAdsLoaded)
    }
    private fun obtainNavEvent(direction: RecommendationsScreenRoutes) {
      /*  when(direction) {
            is RecommendationsScreenRoutes.ToAd -> findNavController().navigate(R.id.RecommendationsToItem, Bundle().apply {
                putParcelable(AdvertisementFragment.ARGS_KEY, direction.ad)
            })
        }
   */ }

    private fun onAdsLoaded(ads: List<AdvertisementWrapper>) {
        recommendationsAdapter.items = ads
    }
}