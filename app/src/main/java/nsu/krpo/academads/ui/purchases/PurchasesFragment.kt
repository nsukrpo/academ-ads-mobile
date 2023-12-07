package nsu.krpo.academads.ui.purchases

import androidx.fragment.app.viewModels
import dagger.hilt.android.AndroidEntryPoint
import nsu.krpo.academads.databinding.FragmentMyPurchasesBinding
import nsu.krpo.academads.ui.base.view.BaseFragment
import nsu.krpo.academads.ui.base.view.viewBinding

@AndroidEntryPoint
class PurchasesFragment : BaseFragment() {

    override val binding by viewBinding { inflater, container ->  FragmentMyPurchasesBinding.inflate(inflater, container, false)}
    override val viewModel: PurchaseViewModel by viewModels()
}