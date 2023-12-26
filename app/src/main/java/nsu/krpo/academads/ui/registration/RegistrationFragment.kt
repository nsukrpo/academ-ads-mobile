package nsu.krpo.academads.ui.registration

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.viewbinding.ViewBinding
import dagger.hilt.android.AndroidEntryPoint
import nsu.krpo.academads.databinding.FragmentRegistrationBinding
import nsu.krpo.academads.ui.base.view.BaseFragment
import nsu.krpo.academads.ui.base.view.viewBinding

@AndroidEntryPoint
class RegistrationFragment : BaseFragment(){
    override val binding by viewBinding { inflater, container ->
        FragmentRegistrationBinding.inflate(inflater, container, false)
    }
    override val viewModel: RegistrationViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupViewListeners()
    }

    private fun setupViewListeners() {
        binding.registrationButton.setOnClickListener {
            viewModel.registration(binding.loginEt.text.toString(), binding.passwordEt.text.toString())
        }
    }
}