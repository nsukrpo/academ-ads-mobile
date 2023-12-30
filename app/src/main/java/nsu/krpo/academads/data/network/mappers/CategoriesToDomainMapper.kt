package nsu.krpo.academads.data.network.mappers

import nsu.krpo.academads.data.network.models.CategoryResponse
import nsu.krpo.academads.domain.model.ads.Category

//TODO: mappers for everything
class CategoriesToDomainMapper {

    fun fromResponse(response: CategoryResponse): List<Category> {
        val domainCategories: MutableList<Category> = mutableListOf()
        response.categoriesItems.forEach {
            when(it.name) {
                "EDUCATIONAL_SUPPLIES" -> domainCategories.add(it.id.toInt(), Category.EDUCATIONAL_STUFF)
                "APPLIANCES" -> domainCategories.add(it.id.toInt(), Category.HOUSEHOLD_APPLIANCE)
                "ELECTRONICS" -> domainCategories.add(it.id.toInt(), Category.DEVICES)
                "EDUCATIONAL_SERVICE" -> domainCategories.add(it.id.toInt(), Category.STUDY_SERVICE)
                "OTHER" -> domainCategories.add(it.id.toInt(), Category.OTHER)
            }
        }
        return domainCategories
    }

    fun getCategoryById(response: CategoryResponse, id: Long): Category {
        var name = ""
        response.categoriesItems.forEach{
            if (it.id == id) {
                name = it.name
            }
        }
        return fromName(name)
    }

    fun fromInt(num: Int): Category {
        return when(num) {
            1 -> return Category.EDUCATIONAL_STUFF
            2 -> return Category.HOUSEHOLD_APPLIANCE
            3 -> return Category.DEVICES
            4 -> return Category.STUDY_SERVICE
            5 -> return Category.OTHER
            else -> return Category.OTHER
        }
    }

    fun fromName(name: String): Category {
        return when(name) {
            "EDUCATIONAL_STUFF" -> Category.EDUCATIONAL_STUFF
            "HOUSEHOLD_APPLIANCE" -> Category.HOUSEHOLD_APPLIANCE
            "DEVICES" -> Category.DEVICES
            "STUDY_SERVICE" -> Category.STUDY_SERVICE
            else -> Category.OTHER
        }
    }
}