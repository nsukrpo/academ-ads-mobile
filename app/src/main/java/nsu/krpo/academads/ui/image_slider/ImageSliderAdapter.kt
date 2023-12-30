package nsu.krpo.academads.ui.image_slider

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.viewpager.widget.PagerAdapter
import androidx.viewpager.widget.ViewPager
import com.bumptech.glide.Glide
import nsu.krpo.academads.R
import nsu.krpo.academads.domain.model.ads.AdvertisementPhoto

class ImageSliderAdapter(
    private val context: Context,
    private val imageList: List<AdvertisementPhoto>
) : PagerAdapter() {

    override fun getCount(): Int = imageList.count()

    override fun isViewFromObject(view: View, `object`: Any): Boolean = (view === `object`)

    //TODO: implement
    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        val view =
            (context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater).inflate(
                R.layout.item_image_slider,
                null
            )
        val ivImages = view.findViewById<ImageView>(R.id.iv_images)


            Glide.with(context)
                .load(imageList[position].photo)
                .into(ivImages)


        val vp = container as ViewPager
        vp.addView(view, 0)
        return view
    }

    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        val vp = container as ViewPager
        val view = `object` as View
        vp.removeView(view)
    }
}