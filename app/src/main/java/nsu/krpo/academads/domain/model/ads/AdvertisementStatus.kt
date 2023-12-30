package nsu.krpo.academads.domain.model.ads


enum class AdvertisementStatus {
    SENT_MODERATION,
    ON_ADS_BOARD,
    DECLINE_UNINFORMATIVE,
    DECLINE_RUDE_WORDS,
    DECLINE_NUDITY,
    DECLINE_VIOLENCE,
    DECLINE_FRAUD,
    WITHDREW,
    BOOKED,
    PURCHASED,
    UNKNOWN
}

fun AdvertisementStatus.title(): String {
    when(this) {
        AdvertisementStatus.SENT_MODERATION -> return "В модерации"
        AdvertisementStatus.ON_ADS_BOARD -> return "Опубликовано"
        AdvertisementStatus.DECLINE_UNINFORMATIVE ->
            return "Отклонено по причине неинформативности"
        AdvertisementStatus.DECLINE_RUDE_WORDS ->
            return "Отклонено по причине содержания нецензурных выражений"
        AdvertisementStatus.DECLINE_NUDITY ->
            return "Отклонено по причине содержания порнографических материалов"
        AdvertisementStatus.DECLINE_VIOLENCE ->
            return "Отклонено по причине содержания сцен насилия"
        AdvertisementStatus.DECLINE_FRAUD ->
            return "Отклонено по причине мошеннического содержания"
        AdvertisementStatus.WITHDREW -> return "Снято с продажи"
        AdvertisementStatus.BOOKED -> return "Забронировано"
        AdvertisementStatus.PURCHASED -> return "Продано"
        AdvertisementStatus.UNKNOWN -> return "Неизвестный статус"
    }
}