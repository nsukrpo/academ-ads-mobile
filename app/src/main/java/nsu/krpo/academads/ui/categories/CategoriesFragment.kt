package nsu.krpo.academads.ui.categories

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import nsu.krpo.academads.databinding.FragmentCategoriesBinding
import nsu.krpo.academads.ui.base.view.BaseFragment
import nsu.krpo.academads.ui.base.view.viewBinding
import nsu.krpo.academads.ui.categories.rv.CategoriesAdapter

class CategoriesFragment : BaseFragment() {

    override val binding by viewBinding { inflater, container ->
        FragmentCategoriesBinding.inflate(inflater, container, false)
    }

    override val viewModel: CategoriesViewModel by viewModels()



    private val categoriesAdapter by lazy {
        CategoriesAdapter(
            viewModel::onItemClicked
        )
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }
}