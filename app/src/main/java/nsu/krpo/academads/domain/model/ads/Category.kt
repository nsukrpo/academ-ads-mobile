package nsu.krpo.academads.domain.model.ads

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
enum class Category : Parcelable {
    EDUCATIONAL_STUFF,
    HOUSEHOLD_APPLIANCE,
    DEVICES,
    STUDY_SERVICE,
    OTHER
}


fun Category.title(): String {
    when (this) {
        Category.EDUCATIONAL_STUFF -> return "Учебные принадлежности"
        Category.HOUSEHOLD_APPLIANCE -> return "Бытовая техника"
        Category.DEVICES -> return "Электроника"
        Category.STUDY_SERVICE -> return "Учебная услуга"
        Category.OTHER -> return "Разное"
        else -> return "Разное"
    }
}

fun Category.number(): Int {
    when (this) {
        Category.EDUCATIONAL_STUFF -> return 1
        Category.HOUSEHOLD_APPLIANCE -> return 2
        Category.DEVICES -> return 3
        Category.STUDY_SERVICE -> return 4
        Category.OTHER -> return 5
    }
}