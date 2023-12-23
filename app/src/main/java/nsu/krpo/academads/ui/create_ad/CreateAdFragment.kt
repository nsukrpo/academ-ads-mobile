package nsu.krpo.academads.ui.create_ad

import androidx.fragment.app.viewModels
import dagger.hilt.android.AndroidEntryPoint
import nsu.krpo.academads.databinding.FragmentCreateAdBinding
import nsu.krpo.academads.ui.base.view.BaseFragment
import nsu.krpo.academads.ui.base.view.viewBinding

@AndroidEntryPoint
class CreateAdFragment : BaseFragment() {
    override val binding by viewBinding { inflater, container ->
        FragmentCreateAdBinding.inflate(inflater, container, false)
    }
    override val viewModel: CreateAdViewModel by viewModels()
}