package nsu.krpo.academads.ui.advertisement

import androidx.fragment.app.viewModels
import dagger.hilt.android.AndroidEntryPoint
import nsu.krpo.academads.databinding.FragmentItemBinding
import nsu.krpo.academads.ui.base.view.BaseFragment
import nsu.krpo.academads.ui.base.view.viewBinding

@AndroidEntryPoint
class AdvertisementFragment : BaseFragment() {

    override val binding by viewBinding { inflater, container -> FragmentItemBinding.inflate(inflater, container, false) }

    override val viewModel: AdvertisementViewModel by viewModels()

    companion object {

        const val ARGS_KEY = "AdvertisementFragment"
    }
}