package nsu.krpo.academads.data.daos.categories

import io.reactivex.rxjava3.core.Single
import nsu.krpo.academads.domain.model.ads.Category
import javax.inject.Inject

class CategoriesDaoStubImpl @Inject constructor() : CategoriesDao {
    override fun getAll(): Single<List<Category>> =
            Single.just(
                listOf(
                    Category.EDUCATIONAL_SUPPLIES,
                    Category.ELECTRONICS,
                    Category.APPLIANCES,
                    Category.EDUCATIONAL_SERVICE,
                    Category.OTHER
                )
            )

    override fun getById(id: Long): Single<Category> {
        return Single.just(Category.OTHER)
    }
}