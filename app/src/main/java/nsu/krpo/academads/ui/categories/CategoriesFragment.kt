package nsu.krpo.academads.ui.categories

import android.content.Context
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import dagger.hilt.android.AndroidEntryPoint
import nsu.krpo.academads.R
import nsu.krpo.academads.databinding.FragmentCategoriesBinding
import nsu.krpo.academads.domain.model.ads.Category
import nsu.krpo.academads.ui.base.view.BaseFragment
import nsu.krpo.academads.ui.base.view.viewBinding
import nsu.krpo.academads.ui.categories.rv.CategoriesAdapter
import nsu.krpo.academads.ui.categories.rv.CategoryWrapper
import nsu.krpo.academads.ui.category.CategoryFragment

@AndroidEntryPoint
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
        binding.rvCategories.adapter = categoriesAdapter
        setupVmObservers()
    }

    private fun setupVmObservers() = viewModel.run {
        categories.observe(viewLifecycleOwner, ::onCategoriesList)
        navEvent.observe(viewLifecycleOwner, ::obtainNavEvent)
    }


    private fun onCategoriesList(categories: List<CategoryWrapper>) {
        categoriesAdapter.items = categories
    }


    private fun obtainNavEvent(direction: CategoriesScreenRoots) = when (direction) {
        is CategoriesScreenRoots.ToCategory -> {
            findNavController().navigate(R.id.ToCategory, Bundle().apply {
                putParcelable(CategoryFragment.ARGS_KEY, direction.category)
            })
        }
    }
}

fun Category.getImage(context: Context) : Drawable = context.resources.getDrawable(R.drawable.category_example)
