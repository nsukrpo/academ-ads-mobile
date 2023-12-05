package nsu.krpo.academads.ui.profile

import android.graphics.BitmapFactory
import android.graphics.drawable.BitmapDrawable
import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import dagger.hilt.android.AndroidEntryPoint
import nsu.krpo.academads.databinding.FragmentProfileBinding
import nsu.krpo.academads.domain.model.ads.AdvertisementStatus
import nsu.krpo.academads.domain.model.ads.User
import nsu.krpo.academads.ui.base.view.BaseFragment
import nsu.krpo.academads.ui.base.view.viewBinding
import nsu.krpo.academads.ui.profile.ads_rv.AdsAdapter
import nsu.krpo.academads.ui.profile.ads_rv.AdvertismentWrapper

@AndroidEntryPoint
class ProfileFragment : BaseFragment() {

    override val binding by viewBinding { inflater, container ->
        FragmentProfileBinding.inflate(inflater, container, false)
    }
    override val viewModel: ProfileViewModel by viewModels()

    private val adsAdapter by lazy {
        AdsAdapter(
            viewModel::onItemClicked
        )
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.rvMyAds.adapter = adsAdapter
        setupSwitchListener()
        setupVmObservers()
    }

    private fun setupVmObservers() = viewModel.run {
        user.observe(viewLifecycleOwner, ::onUserLoaded)
        ads.observe(viewLifecycleOwner, ::onAdsLoaded)
    }

    private fun setupSwitchListener() {
        binding.dontShowSwitch.setOnCheckedChangeListener { button, isChecked ->
            if (isChecked) {
                adsAdapter.items = viewModel.ads.value!!.filter {
                    it.status != AdvertisementStatus.REJECTED
                }
            } else {
                adsAdapter.items = viewModel.myAdsList
            }
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
        binding.registrationDate.text =
            "Дата регистрации: %s".format(
                "%s %s, %s".format(
                    monthByNumber(user.regDate.month),
                    user.regDate.day,
                    user.regDate.year
                )
            )
    }

    private fun onAdsLoaded(ads: List<AdvertismentWrapper>) {
        binding.myAdvertismentsButton.text = "Мои объявления (%s)".format(ads.size)
        adsAdapter.items = ads
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