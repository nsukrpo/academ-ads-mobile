package nsu.krpo.academads.data.network.mappers

import nsu.krpo.academads.data.network.models.CategoryResponse
import nsu.krpo.academads.domain.model.ads.Category

//TODO: mappers for everything
class CategoriesToDomainMapper {

    fun fromResponse(response: CategoryResponse): List<Category> {
        val domainCategories: MutableList<Category> = mutableListOf()
        response.categoriesItems.forEach {
            when(it.name) {
                "EDUCATIONAL_SUPPLIES" -> domainCategories.add(Category.EDUCATIONAL_SUPPLIES)
                "APPLIANCES" -> domainCategories.add(Category.APPLIANCES)
                "ELECTRONICS" -> domainCategories.add(Category.ELECTRONICS)
                "EDUCATIONAL_SERVICE" -> domainCategories.add(Category.EDUCATIONAL_SERVICE)
                "OTHER" -> domainCategories.add(Category.OTHER)
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

    fun fromName(name: String): Category {
        return when(name) {
            "EDUCATIONAL_STUFF" -> Category.EDUCATIONAL_SUPPLIES
            "HOUSEHOLD_APPLIANCE" -> Category.APPLIANCES
            "DEVICES" -> Category.ELECTRONICS
            "STUDY_SERVICE" -> Category.EDUCATIONAL_SERVICE
            else -> Category.OTHER
        }
    }
}