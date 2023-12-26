package nsu.krpo.academads.domain.model.ads

enum class BlockingReason {
    STRIKES_LIMIT,
    GROSS_VIOLATION,
    UNKNOWN
}

fun BlockingReason.title(): String {
    when(this) {
        BlockingReason.STRIKES_LIMIT -> return "Превышен лимит страйков"
        BlockingReason.GROSS_VIOLATION -> return "Грубое нарушение"
        BlockingReason.UNKNOWN -> return "Неизвестно"
    }
}