package nsu.krpo.academads.ui.category

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.viewbinding.ViewBinding
import dagger.hilt.android.AndroidEntryPoint
import nsu.krpo.academads.R
import nsu.krpo.academads.databinding.FragmentCategoryBinding
import nsu.krpo.academads.domain.model.ads.Category
import nsu.krpo.academads.domain.model.ads.title
import nsu.krpo.academads.ui.advertisement.AdvertisementFragment
import nsu.krpo.academads.ui.base.view.BaseFragment
import nsu.krpo.academads.ui.base.view.viewBinding
import nsu.krpo.academads.ui.categories.rv.CategoriesAdapter
import nsu.krpo.academads.ui.category.rv.AdAdapter
import nsu.krpo.academads.ui.category.rv.AdvertisementWrapper

@AndroidEntryPoint
class CategoryFragment : BaseFragment() {


    override val viewModel: CategoryViewModel by viewModels()
    override val binding by viewBinding { inflater, container ->
        FragmentCategoryBinding.inflate(inflater, container, false)
    }

    private val adsAdapter by lazy {
        AdAdapter(
            viewModel::onItemClicked,
            viewModel::onItemLiked,
            viewModel::onItemDisliked
        )
    }

    private lateinit var category: Category

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        category = requireArguments().getParcelable(ARGS_KEY)!!
        binding.rvAds.adapter = adsAdapter
        viewModel.provideCategory(category)
        setupView()
        setupVmObservers()
    }

    private fun setupVmObservers() {
        viewModel.ads.observe(viewLifecycleOwner, ::onAdsLoaded)
        viewModel.navEvent.observe(viewLifecycleOwner, ::obtainNavEvent)
    }

    private fun setupView() {
        binding.title.text = category.title()
    }

    private fun onAdsLoaded(ads: List<AdvertisementWrapper>) {
        adsAdapter.items = ads
    }

    private fun obtainNavEvent(direction: CategoryScreenRoutes) {
        when(direction) {
            is CategoryScreenRoutes.ToAd -> findNavController().navigate(R.id.CategoryToItem, Bundle().apply {
                putParcelable(AdvertisementFragment.ARGS_KEY, direction.ad)
            })
        }
    }

    companion object {
        const val ARGS_KEY = "CategoryFragment"
    }

}