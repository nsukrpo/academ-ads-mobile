package nsu.krpo.academads.ui.profile

import android.graphics.BitmapFactory
import android.graphics.drawable.BitmapDrawable
import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import dagger.hilt.android.AndroidEntryPoint
import nsu.krpo.academads.R
import nsu.krpo.academads.databinding.FragmentProfileBinding
import nsu.krpo.academads.domain.model.ads.AdvertisementStatus
import nsu.krpo.academads.domain.model.ads.User
import nsu.krpo.academads.ui.advertisement.AdvertisementFragment
import nsu.krpo.academads.ui.base.view.BaseFragment
import nsu.krpo.academads.ui.base.view.viewBinding
import nsu.krpo.academads.ui.my_advertisement.MyAdvertisementFragment
import nsu.krpo.academads.ui.profile.ads_rv.AdsAdapter
import nsu.krpo.academads.ui.profile.ads_rv.AdvertismentWrapper
import nsu.krpo.academads.ui.profile.likes_rv.LikedAdapter
import nsu.krpo.academads.ui.profile.likes_rv.LikedWrapper
import nsu.krpo.academads.ui.profile.purchases_rv.PurchaseWrapper
import nsu.krpo.academads.ui.profile.purchases_rv.PurchasesAdapter

@AndroidEntryPoint
class ProfileFragment : BaseFragment() {

    override val binding by viewBinding { inflater, container ->
        FragmentProfileBinding.inflate(inflater, container, false)
    }
    override val viewModel: ProfileViewModel by viewModels()

    private val adsAdapter by lazy {
        AdsAdapter(
            viewModel::onAdItemClicked
        )
    }

    private val purchasesAdapter by lazy {
        PurchasesAdapter(
            viewModel::onPurchaseItemClicked
        )
    }

    private val likesAdapter by lazy {
        LikedAdapter(
            viewModel::onLikedItemClicked
        )
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.rvMyAds.adapter = adsAdapter
        binding.rvMyItems.adapter = purchasesAdapter
        binding.rvMyLikes.adapter = likesAdapter
        setupSwitchListener()
        setupVmObservers()
        setupViewListeners()
    }

    private fun setupVmObservers() = viewModel.run {
        user.observe(viewLifecycleOwner, ::onUserLoaded)
        ads.observe(viewLifecycleOwner, ::onAdsLoaded)
        purchases.observe(viewLifecycleOwner, ::onPurchasesLoaded)
        likes.observe(viewLifecycleOwner, ::onLikesLoaded)
        navEvent.observe(viewLifecycleOwner, ::obtainNavEvent)
    }

    private fun setupSwitchListener() {
        binding.dontShowSwitch.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                adsAdapter.items = viewModel.ads.value!!.filter {
                    (it.ad.status != AdvertisementStatus.DECLINE_FRAUD) &&
                            (it.ad.status != AdvertisementStatus.DECLINE_VIOLENCE) &&
                            (it.ad.status != AdvertisementStatus.DECLINE_NUDITY) &&
                            (it.ad.status != AdvertisementStatus.DECLINE_RUDE_WORDS) &&
                            (it.ad.status != AdvertisementStatus.DECLINE_UNINFORMATIVE)
                }
            } else {
                adsAdapter.items = viewModel.myAdsList
            }
        }
    }

    private fun setupViewListeners() {
        binding.run {
            myItemsButton.setOnClickListener { viewModel.onMyItems() }
            myAdvertismentsButton.setOnClickListener { viewModel.onMyAds() }
            myLikesButton.setOnClickListener { viewModel.onMyLikes() }
            createAdButton.setOnClickListener { viewModel.onCreateAd() }
            bansButton.setOnClickListener { viewModel.onBans() }
        }
    }

    private fun obtainNavEvent(direction: ProfileScreenRoutes) = when (direction) {
        is ProfileScreenRoutes.ToMyAds -> {
            findNavController().navigate(R.id.ToMyAds)
        }

        is ProfileScreenRoutes.ToMyAd -> {
            findNavController().navigate(R.id.ToMyAd, Bundle().apply {
                putParcelable(MyAdvertisementFragment.ARGS_KEY, direction.ad)
            })
        }

        is ProfileScreenRoutes.ToAd -> {
            findNavController().navigate(R.id.ToItem, Bundle().apply {
                putParcelable(AdvertisementFragment.ARGS_KEY, direction.ad)
            })
        }

        is ProfileScreenRoutes.ToMyPurchases -> {
            findNavController().navigate(R.id.ToPurchases)
        }

        is ProfileScreenRoutes.ToLikes -> {
            findNavController().navigate(R.id.ToLikes)
        }

        is ProfileScreenRoutes.ToCreateAd -> {
            // TODO: findNavController().navigate()
        }

        is ProfileScreenRoutes.ToBans -> {
            findNavController().navigate(R.id.ToBans)
        }
    }

    private fun onUserLoaded(user: User) {
        binding.userAvatar.setImageDrawable(
            BitmapDrawable(
                BitmapFactory.decodeByteArray(
                    user.avatar.photo, 0, user.avatar.photo.size
                )
            )
        )
        binding.userName.text = user.name
        binding.registrationDate.text = "Дата регистрации: %s".format(
            "%s %s, %s".format(
                monthByNumber(user.regDate.month), user.regDate.day, user.regDate.year
            )
        )
    }

    private fun onAdsLoaded(ads: List<AdvertismentWrapper>) {
        binding.myAdvertismentsButton.text = "Мои объявления (%s)".format(ads.size)
        adsAdapter.items = ads
    }

    private fun onPurchasesLoaded(purchases: List<PurchaseWrapper>) {
        binding.myItemsButton.text = "Мои покупки (%s)".format(purchases.size)
        purchasesAdapter.items = purchases
    }

    private fun onLikesLoaded(likes: List<LikedWrapper>) {
        binding.myLikesButton.text = "Избранное (%s)".format(likes.size)
        likesAdapter.items = likes
    }

    private fun monthByNumber(number: Int): String {
        when (number) {
            0 -> return "Jan"
            1 -> return "Feb"
            2 -> return "Mar"
            3 -> return "Apr"
            4 -> return "May"
            5 -> return "Jun"
            6 -> return "Jul"
            7 -> return "Aug"
            8 -> return "Sep"
            9 -> return "Oct"
            10 -> return "Nov"
            11 -> return "Dec"
        }
        return "Unknown"
    }
}