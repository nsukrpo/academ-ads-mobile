package nsu.krpo.academads.data.daos.categories

import io.reactivex.rxjava3.core.Single
import nsu.krpo.academads.domain.model.ads.Category

interface CategoriesDao {

    fun getAll(): Single<List<Category>>

    fun getById(id: Long): Single<Category>
}