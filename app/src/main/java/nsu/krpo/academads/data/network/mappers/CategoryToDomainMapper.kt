package nsu.krpo.academads.data.network.mappers

import nsu.krpo.academads.data.network.models.CategoryResponse
import nsu.krpo.academads.domain.model.ads.Category

//TODO: mappers for everything
class CategoryToDomainMapper {

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
}