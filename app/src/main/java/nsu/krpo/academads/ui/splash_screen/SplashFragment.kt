package nsu.krpo.academads.ui.splash_screen

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import dagger.hilt.android.AndroidEntryPoint
import nsu.krpo.academads.R
import nsu.krpo.academads.databinding.FragmentSplashBinding
import nsu.krpo.academads.ui.base.view.BaseFragment
import nsu.krpo.academads.ui.base.view.viewBinding

@AndroidEntryPoint
class SplashFragment : BaseFragment() {

    override val binding by viewBinding { inflater, container ->
        FragmentSplashBinding.inflate(inflater, container, false)
    }

    override val viewModel: SplashFragmentViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupVmObservers()
    }

    private fun setupVmObservers() = viewModel.run {
        navEvent.observe(viewLifecycleOwner, ::obtainNavEvent)
    }

    private fun obtainNavEvent(event: SplashRoutes) = when (event) {
        SplashRoutes.ToLogin -> findNavController().navigate(R.id.toLogin)
        SplashRoutes.ToMainScreen -> findNavController().navigate(R.id.toMainScreen)
    }
}