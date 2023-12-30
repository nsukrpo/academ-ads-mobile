package nsu.krpo.academads.ui.purchases

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import dagger.hilt.android.AndroidEntryPoint
import nsu.krpo.academads.R
import nsu.krpo.academads.databinding.FragmentMyPurchasesBinding
import nsu.krpo.academads.ui.advertisement.AdvertisementFragment
import nsu.krpo.academads.ui.base.view.BaseFragment
import nsu.krpo.academads.ui.base.view.viewBinding
import nsu.krpo.academads.ui.my_likes.MyLikesScreenRoutes
import nsu.krpo.academads.ui.my_likes.rv.LikedAdWrapper
import nsu.krpo.academads.ui.my_likes.rv.LikedAdapter
import nsu.krpo.academads.ui.purchases.rv.PurchaseAdapter
import nsu.krpo.academads.ui.purchases.rv.PurchaseWrapper

@AndroidEntryPoint
class PurchasesFragment : BaseFragment() {

    override val binding by viewBinding { inflater, container ->  FragmentMyPurchasesBinding.inflate(inflater, container, false)}
    override val viewModel: PurchaseViewModel by viewModels()

    private val adapter by lazy {
        PurchaseAdapter(
            viewModel::onItemClicked,
        )
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.rvPurchases.adapter = adapter

        setupVmObservers()
    }

    private fun setupVmObservers() {
        viewModel.purchases.observe(viewLifecycleOwner, ::onAdsLoaded)
        viewModel.navEvent.observe(viewLifecycleOwner, ::obtainNavEvent)
    }

    private fun onAdsLoaded(ads: List<PurchaseWrapper>) {
        adapter.items = ads
    }

    private fun obtainNavEvent(dir: PurchasesScreenRoutes) {
        when(dir) {
            is PurchasesScreenRoutes.ToAd -> findNavController().navigate(R.id.PurchasesToAd, Bundle().apply {
                putParcelable(AdvertisementFragment.ARGS_KEY, dir.ad)
            })
        }
    }
}