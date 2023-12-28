package nsu.krpo.academads.ui.my_likes

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import dagger.hilt.android.AndroidEntryPoint
import nsu.krpo.academads.R
import nsu.krpo.academads.databinding.FragmentLikedBinding
import nsu.krpo.academads.ui.advertisement.AdvertisementFragment
import nsu.krpo.academads.ui.base.view.BaseFragment
import nsu.krpo.academads.ui.base.view.viewBinding
import nsu.krpo.academads.ui.my_likes.rv.LikedAdWrapper
import nsu.krpo.academads.ui.my_likes.rv.LikedAdapter

@AndroidEntryPoint
class MyLikesFragment : BaseFragment() {

    override val binding by viewBinding { inflater, container -> FragmentLikedBinding.inflate(inflater, container, false) }
    override val viewModel: MyLikesViewModel by viewModels()

    private val adapter by lazy {
        LikedAdapter(
            viewModel::onItemClicked,
            viewModel::onItemDisliked
        )
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.rvLiked.adapter = adapter

        setupVmObservers()
    }

    private fun setupVmObservers() {
        viewModel.ads.observe(viewLifecycleOwner, ::onAdsLoaded)
        viewModel.navEvent.observe(viewLifecycleOwner, ::obtainNavEvent)
    }

    private fun onAdsLoaded(ads: List<LikedAdWrapper>) {
        adapter.items = ads
    }

    private fun obtainNavEvent(dir: MyLikesScreenRoutes) {
        when(dir) {
            is MyLikesScreenRoutes.ToAd -> findNavController().navigate(R.id.ToLikedAd, Bundle().apply {
                putParcelable(AdvertisementFragment.ARGS_KEY, dir.ad)
            })
        }
    }
}