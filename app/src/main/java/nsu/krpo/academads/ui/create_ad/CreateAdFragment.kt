package nsu.krpo.academads.ui.create_ad

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import dagger.hilt.android.AndroidEntryPoint
import nsu.krpo.academads.R
import nsu.krpo.academads.databinding.FragmentCreateAdBinding
import nsu.krpo.academads.ui.base.view.BaseFragment
import nsu.krpo.academads.ui.base.view.viewBinding

@AndroidEntryPoint
class CreateAdFragment : BaseFragment() {
    override val binding by viewBinding { inflater, container ->
        FragmentCreateAdBinding.inflate(inflater, container, false)
    }
    override val viewModel: CreateAdViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.createAdButton.setOnClickListener {
            findNavController().navigate(R.id.ToProf)
        }
    }
}