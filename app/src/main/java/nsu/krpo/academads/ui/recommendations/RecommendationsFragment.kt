package nsu.krpo.academads.ui.recommendations

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import nsu.krpo.academads.databinding.FragmentRecomendationsBinding
import nsu.krpo.academads.ui.base.view.BaseFragment
import nsu.krpo.academads.ui.base.view.viewBinding

class RecommendationsFragment : BaseFragment() {

    override val binding by viewBinding { inflater, container ->
        FragmentRecomendationsBinding.inflate(inflater, container, false)
    }
    override val viewModel: RecommendationsViewModel by viewModels()


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }
}