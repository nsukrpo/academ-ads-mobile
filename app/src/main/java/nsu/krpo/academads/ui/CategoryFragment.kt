package nsu.krpo.academads.ui

import android.os.Bundle
import android.view.View
import nsu.krpo.academads.ui.base.view.BaseFragment
import nsu.krpo.academads.ui.base.view.BaseViewModel
import nsu.krpo.academads.ui.base.view.ViewBindingDelegate

class CategoryFragment : BaseFragment() {

    override var _viewBindingDelegate: ViewBindingDelegate<*>?
        get() = super._viewBindingDelegate
        set(value) {}
    override val viewModel: BaseViewModel
        get() = TODO("Not yet implemented")

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }


}