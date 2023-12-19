package nsu.krpo.academads.ui.my_ads

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import dagger.hilt.android.AndroidEntryPoint
import nsu.krpo.academads.databinding.FragmentMyAdsBinding
import nsu.krpo.academads.ui.base.view.BaseFragment
import nsu.krpo.academads.ui.base.view.viewBinding

@AndroidEntryPoint
class MyAdsFragment : BaseFragment() {
    override val viewModel: MyAdsViewModel by viewModels()

    override val binding by viewBinding { inflater, container ->
        FragmentMyAdsBinding.inflate(inflater, container,false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupVmObservers()
    }

    private fun setupVmObservers() {

    }
}