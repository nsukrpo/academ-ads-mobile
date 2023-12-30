package nsu.krpo.academads.ui.category

import android.app.DatePickerDialog
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
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.ZoneId
import java.util.Calendar
import java.util.Locale

@AndroidEntryPoint
class CategoryFragment : BaseFragment() {


    override val viewModel: CategoryViewModel by viewModels()
    override val binding by viewBinding { inflater, container ->
        FragmentCategoryBinding.inflate(inflater, container, false)
    }

    private val calendar = Calendar.getInstance()

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
        setupViewListeners()
    }

    private fun setupVmObservers() {
        viewModel.ads.observe(viewLifecycleOwner, ::onAdsLoaded)
        viewModel.navEvent.observe(viewLifecycleOwner, ::obtainNavEvent)
    }

    private fun setupView() {
        binding.title.text = category.title()

    }

    private fun setupViewListeners() {
        binding.dateButton.setOnClickListener {
            showDatePicker()
        }
    }

    private fun showDatePicker() {
        // Create a DatePickerDialog
        val datePickerDialog = DatePickerDialog(
            requireContext(), { DatePicker, year: Int, monthOfYear: Int, dayOfMonth: Int ->
                // Create a new Calendar instance to hold the selected date
                val selectedDate = Calendar.getInstance()
                // Set the selected date using the values received from the DatePicker dialog
                selectedDate.set(year, monthOfYear, dayOfMonth)
                // Create a SimpleDateFormat to format the date as "dd/MM/yyyy"
                val dateFormat = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
                // Format the selected date into a string
                val formattedDate = dateFormat.format(selectedDate.time)
                // Update the TextView to display the selected date with the "Selected Date: " prefix
                binding.tvSelectedDate.text = "Selected Date: $formattedDate"
                viewModel.findAdsByDate(LocalDateTime.ofInstant(selectedDate.toInstant(), ZoneId.systemDefault()).toLocalDate())
            },
            calendar.get(Calendar.YEAR),
            calendar.get(Calendar.MONTH),
            calendar.get(Calendar.DAY_OF_MONTH)
        )
        // Show the DatePicker dialog
        datePickerDialog.show()
    }

    private fun onAdsLoaded(ads: List<AdvertisementWrapper>) {
        adsAdapter.items = ads
    }

    private fun obtainNavEvent(direction: CategoryScreenRoutes) {
        when (direction) {
            is CategoryScreenRoutes.ToAd -> findNavController().navigate(
                R.id.CategoryToItem,
                Bundle().apply {
                    putParcelable(AdvertisementFragment.ARGS_KEY, direction.ad)
                })
        }
    }

    companion object {
        const val ARGS_KEY = "CategoryFragment"
    }

}