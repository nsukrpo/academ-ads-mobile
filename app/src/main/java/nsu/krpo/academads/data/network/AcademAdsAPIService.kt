package nsu.krpo.academads.data.network

import io.reactivex.rxjava3.core.Single
import nsu.krpo.academads.data.network.models.AdvertisementCreate
import nsu.krpo.academads.data.network.models.AdvertisementResponse
import nsu.krpo.academads.data.network.models.AdvertisementUpdate
import nsu.krpo.academads.data.network.models.ApiResponse
import nsu.krpo.academads.data.network.models.Avatar
import nsu.krpo.academads.data.network.models.BlockingCreate
import nsu.krpo.academads.data.network.models.BlockingResponse
import nsu.krpo.academads.data.network.models.BlockingUpdate
import nsu.krpo.academads.data.network.models.BookingRequest
import nsu.krpo.academads.data.network.models.BookingResponse
import nsu.krpo.academads.data.network.models.CategoryResponse
import nsu.krpo.academads.data.network.models.FavoriteAdvertisementsRequest
import nsu.krpo.academads.data.network.models.FavoriteCategoryRequest
import nsu.krpo.academads.data.network.models.FavoriteUserRequest
import nsu.krpo.academads.data.network.models.IdResponse
import nsu.krpo.academads.data.network.models.LoginCreate
import nsu.krpo.academads.data.network.models.MessageResponse
import nsu.krpo.academads.data.network.models.Photo
import nsu.krpo.academads.data.network.models.PurchaseRequest
import nsu.krpo.academads.data.network.models.PurchaseResponse
import nsu.krpo.academads.data.network.models.StrikeCreate
import nsu.krpo.academads.data.network.models.StrikeResponse
import nsu.krpo.academads.data.network.models.StrikeUpdate
import nsu.krpo.academads.data.network.models.TokenRequest
import nsu.krpo.academads.data.network.models.TokenResponse
import nsu.krpo.academads.data.network.models.UserCreate
import nsu.krpo.academads.data.network.models.UserResponse
import nsu.krpo.academads.data.network.models.UserUpdate
import okhttp3.RequestBody
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Headers
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path
import retrofit2.http.Query

interface AcademAdsAPIService {
    //TODO: add rest methods using Retrofit2

    @GET("/category")
    fun getCategoriesList(): Single<CategoryResponse>
    @GET("/user/{id}")
    fun getUserById(@Path("id") userId: Long): Call<UserResponse>
    @PUT("/user/{id}")
    fun updateUser(@Path("id") userId: Long, @Body updatedUser: UserUpdate): Single<ApiResponse>
    @POST("/user")
    fun createUser(@Body newUser: UserCreate): Single<ApiResponse>
    @GET("/user")
    fun getUsers(
        @Query("rejected_ads") rejectedAds: Long?,
        @Query("reject_reason") rejectReason: String?,
        @Query("published_ads") publishedAds: Long?,
        @Query("blockings") blockings: Int?,
        @Query("blocking_status") blockingStatus: Int?
    ): Single<List<UserResponse>>
    @PUT("/login")
    fun createLogin(@Body loginCreate: LoginCreate): Single<ApiResponse>
    @POST("/login")
    fun loginUser(@Body tokenRequest: TokenRequest): Single<ResponseBody>

    @GET("/advertisement/{id}")
    fun getAdvertisementById(@Path("id") advertisementId: Long): Single<AdvertisementResponse>
    @PUT("/advertisement/{id}")
    fun updateAdvertisement(@Path("id") advertisementId: Long, @Body updatedAdvertisement: AdvertisementUpdate): Single<ApiResponse>
    @DELETE("/advertisement/{id}")
    fun deleteAdvertisement(@Path("id") advertisementId: Long): Single<ApiResponse>
    @POST("/advertisement")
    fun createAdvertisement(@Body createAdvertisement: AdvertisementCreate): Single<ApiResponse>

    @GET("/advertisement")
    fun getAdvertisements(
            @Query("category") categoryId: Long? = null,
            @Query("date") publicationDate: String? = null,
            @Query("countWatch") watchCount: Long? = null,
    ): Call<List<AdvertisementResponse>>
    @GET("/media/avatar/{id}")
    fun getAvatarById(@Path("id") avatarId: Long): Single<Avatar>
    @POST("/media/avatar")
    fun createAvatar(@Query("user_id") userId: Long,
                             @Body createAvatar: Avatar
    ): Single<ApiResponse>
    @GET("/media/photos/{id}")
    fun getPhotoById(@Path("id") photoId: Long): Single<ResponseBody>
    @POST("/media/photos")
    fun createPhoto(@Body createPhoto: RequestBody): Single<ApiResponse>
    @GET("/blocking/{id}")
    fun getBlockingById(@Path("id") blockingId: Long): Single<BlockingResponse>
    @PUT("/blocking/{id}")
    fun updateBlockingById(@Path("id") blockingId: Long, @Body updateBlocking: BlockingUpdate): Single<BlockingResponse>
    @GET("/blocking")
    fun getBlockingByUserId(@Query("user_id") userId: Long): Single<List<BlockingResponse>>
    @POST("/blocking")
    fun createBlocking(@Body newBlocking: BlockingCreate): Single<BlockingResponse>
    @GET("/strike/{id}")
    fun getStrikeById(@Path("id") strikeId: Long): Single<StrikeResponse>
    @PUT("/strike/{id}")
    fun updateStrikeById(@Path("id") strikeId: Long, @Body updateStrike: StrikeUpdate): Single<ApiResponse>
    @GET("/strike")
    fun getStrikesByUserId(@Query("user_id") userId: Long): Single<List<StrikeResponse>>
    @POST("/strike")
    fun createStrike(@Body createStrike: StrikeCreate): Single<ApiResponse>
    @GET("/message")
    fun getMessages(
            @Query("from") fromUserId: Long,
            @Query("to") toUserId: Long
    ): Single<List<MessageResponse>>
   // @POST("/message")
   // suspend fun createMessage(@Body newMessage: NewMessage): Response<ApiResponse>
    @GET("/message/{id}")
    fun getMessageById(@Path("id") messageId: Long): Single<MessageResponse>
    @GET("/favorite/advertisement")
    fun getFavoriteAdvertisements(@Query("user_id") userId: Long): Single<List<AdvertisementResponse>>
    @POST("/favorite/advertisement")
    fun addToFavoriteAdvertisements(@Body favoriteAdvertisementsRequest: FavoriteAdvertisementsRequest): Single<ApiResponse>
    @DELETE("/favorite/advertisement")
    fun removeFromFavorites(
            @Query("user_id") userId: Long,
            @Query("ads_id") advertisementId: Long
    ): Single<ApiResponse>
    @GET("/favorite/categories")
    fun getFavoriteCategories(@Query("user_id") userId: Long): Single<CategoryResponse>
    @POST("/favorite/categories")
    fun addToFavoriteCategories(@Body favoriteCategoryRequest: FavoriteCategoryRequest): Single<ApiResponse>
    @DELETE("/favorite/categories")
    fun removeFromFavoriteCategories(
            @Query("user_id") userId: Long,
            @Query("category_id") categoryId: Long
    ): Single<ApiResponse>
    @GET("/favorite/user")
    fun getFavoriteUser(@Query("subject_id") subjectId: Long): Single<List<UserResponse>>
    @POST("/favorite/user")
    fun addToFavoriteUsers(@Body favoriteUserRequest: FavoriteUserRequest): Single<ApiResponse>
    @DELETE("/favorite/user")
    fun removeFromFavoriteUsers(
            @Query("subject_id") userId: Long,
            @Query("object_id") objectId: Long
    ): Single<ApiResponse>
    @GET("/booking")
    fun getBookings(@Query("user_id") userId: Long): Single<List<BookingResponse>>
    @POST("/booking")
    fun createBooking(@Body bookingRequest: BookingRequest): Single<IdResponse>
    @DELETE("/booking")
    fun cancelBooking(@Query("booking_id") bookingId: Long): Single<ApiResponse>
    @GET("/purchase")
    fun getPurchases(@Query("user_id") userId: Long): Single<List<PurchaseResponse>>
    @POST("/purchase")
    fun createPurchase(@Body purchaseRequest: PurchaseRequest): Single<ApiResponse>
    @GET("/sales")
    fun getSales(@Query("user_id") userId: Long): Single<List<PurchaseResponse>>

}