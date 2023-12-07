package nsu.krpo.academads.domain.model.ads


enum class AdvertisementStatus {
    MODERATING,
    GRANTED,
    REJECTED,
    WITHDRAWN_FROM_SALE,
    BOOKED,
    SOLD,
}

fun AdvertisementStatus.title(): String {
    when(this) {
        AdvertisementStatus.MODERATING -> return "В модерации"
        AdvertisementStatus.GRANTED -> return "Опубликовано"
        AdvertisementStatus.REJECTED -> return "Отклонено"
        AdvertisementStatus.WITHDRAWN_FROM_SALE -> return "Снято с продажи"
        AdvertisementStatus.BOOKED -> return "Забронировано"
        AdvertisementStatus.SOLD -> return "Продано"
    }
}