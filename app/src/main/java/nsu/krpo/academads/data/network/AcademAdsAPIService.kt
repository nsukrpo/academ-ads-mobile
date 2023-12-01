package nsu.krpo.academads.data.network

import nsu.krpo.academads.data.network.models.CategoryResponse
import retrofit2.Response
import retrofit2.http.GET

interface AcademAdsAPIService {
    //TODO: add rest methods using Retrofit2

    @GET("/category")
    suspend fun getCategoriesList(): Response<CategoryResponse>
}