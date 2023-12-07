package nsu.krpo.academads.ui.my_advertisement

import androidx.fragment.app.viewModels
import dagger.hilt.android.AndroidEntryPoint
import nsu.krpo.academads.databinding.FragmentMyAdBinding
import nsu.krpo.academads.ui.base.view.BaseFragment
import nsu.krpo.academads.ui.base.view.viewBinding

@AndroidEntryPoint
class MyAdvertisementFragment : BaseFragment() {

    override val binding by viewBinding { inflater, container -> FragmentMyAdBinding.inflate(inflater, container, false) }
    override val viewModel: MyAdvertisementViewModel by viewModels()

    companion object {

        const val ARGS_KEY = "MyAdvertisementFragment"
    }
}