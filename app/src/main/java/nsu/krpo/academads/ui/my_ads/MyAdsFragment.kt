package nsu.krpo.academads.ui.my_ads

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import dagger.hilt.android.AndroidEntryPoint
import nsu.krpo.academads.R
import nsu.krpo.academads.databinding.FragmentMyAdsBinding
import nsu.krpo.academads.ui.base.view.BaseFragment
import nsu.krpo.academads.ui.base.view.viewBinding
import nsu.krpo.academads.ui.my_ads.rv.AdAdapter
import nsu.krpo.academads.ui.my_advertisement.MyAdvertisementFragment

@AndroidEntryPoint
class MyAdsFragment : BaseFragment() {
    override val viewModel: MyAdsViewModel by viewModels()

    private val adsAdapter by lazy {
        AdAdapter(
            viewModel::onItemClicked,
        )
    }

    override val binding by viewBinding { inflater, container ->
        FragmentMyAdsBinding.inflate(inflater, container,false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.rvAds.adapter = adsAdapter
        setupVmObservers()
    }

    private fun setupVmObservers() {
        viewModel.navEvent.observe(viewLifecycleOwner, ::obtainNav)
        viewModel.ads.observe(viewLifecycleOwner) {
            adsAdapter.items = it
        }
    }

    private fun obtainNav(dir: MyAdsDirs) {
        when(dir) {
            is MyAdsDirs.ToMyAd -> findNavController().navigate(R.id.ToItemMyAd, Bundle().apply {
                putParcelable(MyAdvertisementFragment.ARGS_KEY, dir.ad)
            })
        }
    }
}