package nsu.krpo.academads.ui.categories

import android.content.Context
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import dagger.hilt.android.AndroidEntryPoint
import nsu.krpo.academads.R
import nsu.krpo.academads.databinding.FragmentCategoriesBinding
import nsu.krpo.academads.domain.model.ads.Category
import nsu.krpo.academads.ui.base.view.BaseFragment
import nsu.krpo.academads.ui.base.view.viewBinding
import nsu.krpo.academads.ui.categories.rv.CategoriesAdapter
import nsu.krpo.academads.ui.categories.rv.CategoryWrapper

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
        //navEvent.observe(viewLifecycleOwner, ::obtainNavEvent)
    }

    private fun onCategoriesList(animals: List<CategoryWrapper>) {
        categoriesAdapter.items = animals
    }

   /* private fun obtainNavEvent(direction: AnimalsListDirections) = when (direction) {
        is AnimalsListDirections.ToAnimalDetails -> {
            findNavController().navigate(R.id.toAnimalDetails, Bundle().apply {
                putParcelable(AnimalDetailsFragment.ARGS_KEY, direction.animal)
            })
        }

        AnimalsListDirections.ToFilter -> {
            findNavController().navigate(R.id.toAnimalFilter)
        }
    }*/



}

fun Category.getImage(context: Context) : Drawable = context.resources.getDrawable(R.drawable.category_example)
