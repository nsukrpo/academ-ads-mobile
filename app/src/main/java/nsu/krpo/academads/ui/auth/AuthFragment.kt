package nsu.krpo.academads.ui.auth

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import dagger.hilt.android.AndroidEntryPoint
import nsu.krpo.academads.R
import nsu.krpo.academads.databinding.FragmentAuthBinding
import nsu.krpo.academads.databinding.FragmentRecomendationsBinding
import nsu.krpo.academads.ui.base.view.BaseFragment
import nsu.krpo.academads.ui.base.view.BaseViewModel
import nsu.krpo.academads.ui.base.view.viewBinding

@AndroidEntryPoint
class AuthFragment : BaseFragment() {

    override val binding by viewBinding { inflater, container ->
        FragmentAuthBinding.inflate(inflater, container, false)
    }

    override val viewModel: AuthViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupViewListeners()
        setupVmObservers()
    }

    private fun setupViewListeners() {
        binding.run {
            logInButton.setOnClickListener { viewModel.login(loginEt.text.toString(), passwordEt.text.toString()) }
            registrationButton.setOnClickListener { viewModel.registration() }
        }
    }

    private fun setupVmObservers() {
        viewModel.errorMessage.observe(viewLifecycleOwner, ::handleError)
        viewModel.navEvent.observe(viewLifecycleOwner, ::obtainNavEvent)
    }

    private fun handleError(error: String) {
        binding.errorTv.text = error
    }

    private fun obtainNavEvent(direction: AuthScreenRoutes) {
        when(direction) {
            is AuthScreenRoutes.ToRegistration -> findNavController().navigate(R.id.ToRegistration)
            is AuthScreenRoutes.ToMainScreen -> findNavController().navigate(R.id.AuthToMainScreen)
        }
    }
}