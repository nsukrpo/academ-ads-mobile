package nsu.krpo.academads.ui.my_likes

import androidx.fragment.app.viewModels
import dagger.hilt.android.AndroidEntryPoint
import nsu.krpo.academads.databinding.FragmentLikedBinding
import nsu.krpo.academads.ui.base.view.BaseFragment
import nsu.krpo.academads.ui.base.view.viewBinding

@AndroidEntryPoint
class MyLikesFragment : BaseFragment() {

    override val binding by viewBinding { inflater, container -> FragmentLikedBinding.inflate(inflater, container, false) }
    override val viewModel: MyLikesViewModel by viewModels()
}