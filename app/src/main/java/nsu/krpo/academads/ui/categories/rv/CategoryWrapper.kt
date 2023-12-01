package nsu.krpo.academads.ui.categories.rv

import android.content.res.Resources
import android.graphics.drawable.Drawable
import nsu.krpo.academads.R
import nsu.krpo.academads.domain.model.ads.Category

class CategoryWrapper (
    val category: Category,
    val itemsCount: Int,
    val image: Drawable


)

fun Category.getImage() : Drawable = Resources.getSystem().getDrawable(R.drawable.category_example)

