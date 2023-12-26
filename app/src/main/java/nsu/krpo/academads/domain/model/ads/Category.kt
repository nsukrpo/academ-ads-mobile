package nsu.krpo.academads.domain.model.ads

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
enum class Category : Parcelable {
        EDUCATIONAL_SUPPLIES,
        APPLIANCES,
        ELECTRONICS,
        EDUCATIONAL_SERVICE,
        OTHER
}


fun Category.title(): String {
        when(this) {
                Category.EDUCATIONAL_SUPPLIES -> return "Учебные принадлежности"
                Category.APPLIANCES -> return "Бытовая техника"
                Category.ELECTRONICS -> return "Электроника"
                Category.EDUCATIONAL_SERVICE -> return "Учебная услуга"
                Category.OTHER -> return "Разное"
                else -> return "Разное"
        }
}