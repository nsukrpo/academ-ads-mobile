package nsu.krpo.academads.ui.profile

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import nsu.krpo.academads.databinding.FragmentProfileBinding
import nsu.krpo.academads.ui.base.view.BaseFragment
import nsu.krpo.academads.ui.base.view.viewBinding

class ProfileFragment : BaseFragment() {

    override val binding by viewBinding { inflater, container ->
        FragmentProfileBinding.inflate(inflater, container, false)
    }
    override val viewModel: ProfileViewModel by viewModels()


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }
}