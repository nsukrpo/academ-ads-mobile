package nsu.krpo.academads.domain.model.ads

enum class Category {
        EDUCATIONAL_SUPPLIES,
        APPLIANCES,
        ELECTRONICS,
        EDUCATIONAL_SERVICE,
        OTHER,
}

fun Category.title(): String {
        when(this) {
                Category.EDUCATIONAL_SUPPLIES -> return "Учебные принадлежности"
                Category.APPLIANCES -> return "Бытовая техника"
                Category.ELECTRONICS -> return "Электроника"
                Category.EDUCATIONAL_SERVICE -> return "Учебная услуга"
                Category.OTHER -> return "Другое"
                else -> return "Другое"
        }
}