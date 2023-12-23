package nsu.krpo.academads.ui.bans

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import dagger.hilt.android.AndroidEntryPoint
import nsu.krpo.academads.databinding.FragmentBansBinding
import nsu.krpo.academads.ui.bans.bans_rv.BanWrapper
import nsu.krpo.academads.ui.bans.bans_rv.BansAdapter
import nsu.krpo.academads.ui.base.view.BaseFragment
import nsu.krpo.academads.ui.base.view.viewBinding
import nsu.krpo.academads.ui.categories.rv.CategoriesAdapter

@AndroidEntryPoint
class BansFragment : BaseFragment() {
    override val binding by viewBinding { inflater, container ->
        FragmentBansBinding.inflate(inflater, container, false)
    }
    override val viewModel: BansViewModel by viewModels()

    private val bansAdapter by lazy {
        BansAdapter()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.rvBans.adapter = bansAdapter
        setupVmObservers()
    }

    private fun setupVmObservers() {
        viewModel.categories.observe(
            viewLifecycleOwner,
            ::onGetBans
        )
    }

    private fun onGetBans(bans: List<BanWrapper>) {
        bansAdapter.items = bans
    }
}