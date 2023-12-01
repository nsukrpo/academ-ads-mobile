package nsu.krpo.academads.ui.main_screen

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI.setupWithNavController
import dagger.hilt.android.AndroidEntryPoint
import nsu.krpo.academads.R
import nsu.krpo.academads.databinding.FragmentMainScreenBinding
import nsu.krpo.academads.ui.base.view.BaseFragment
import nsu.krpo.academads.ui.base.view.viewBinding

@AndroidEntryPoint
class MainScreenFragment : BaseFragment() {

    override val binding by viewBinding { inflater, container ->
        FragmentMainScreenBinding.inflate(inflater, container, false)
    }
    override val viewModel: MainScreenViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupBottomMenuController()
        setupVmObservers()
    }

    private fun setupVmObservers() = viewModel.run {

    }

    private fun setupBottomMenuController() {
        val navHostFragment =
            childFragmentManager.findFragmentById(R.id.fragment_container_view) as NavHostFragment
        val navController = navHostFragment.navController
        setupWithNavController(binding.bottomMenuView, navController)

    }
}