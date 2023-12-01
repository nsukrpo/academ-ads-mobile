package nsu.krpo.academads.ui

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import dagger.hilt.android.AndroidEntryPoint
import nsu.krpo.academads.databinding.FragmentRecomendationsBinding
import nsu.krpo.academads.ui.base.view.BaseFragment
import nsu.krpo.academads.ui.base.view.BaseViewModel
import nsu.krpo.academads.ui.base.view.viewBinding

@AndroidEntryPoint
class AuthFragment : BaseFragment() {

    override val binding by viewBinding { inflater, container ->
        FragmentRecomendationsBinding.inflate(inflater, container, false)
    }

    override val viewModel: BaseViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }
}