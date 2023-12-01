package nsu.krpo.academads.ui.categories

import nsu.krpo.academads.domain.model.ads.Category

sealed interface CategoriesScreenRoots {

    class ToCategory(val category: Category) : CategoriesScreenRoots

}