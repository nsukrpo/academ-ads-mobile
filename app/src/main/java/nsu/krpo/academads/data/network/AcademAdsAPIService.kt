package nsu.krpo.academads.data.network

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
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path
import retrofit2.http.Query

interface AcademAdsAPIService {
    //TODO: add rest methods using Retrofit2

    @GET("/category")
    suspend fun getCategoriesList(): Response<CategoryResponse>
    @GET("/user/{id}")
    suspend fun getUserById(@Path("id") userId: Long): Response<UserResponse>
    @PUT("/user/{id}")
    suspend fun updateUser(@Path("id") userId: Long, @Body updatedUser: UserUpdate): Response<ApiResponse>
    @POST("/user")
    suspend fun createUser(@Body newUser: UserCreate): Response<ApiResponse>
    @GET("/user")
    suspend fun getUsers(
        @Query("rejected_ads") rejectedAds: Long?,
        @Query("reject_reason") rejectReason: String?,
        @Query("published_ads") publishedAds: Long?,
        @Query("blockings") blockings: Int?,
        @Query("blocking_status") blockingStatus: Int?
    ): Response<List<UserResponse>>
    @PUT("/login")
    suspend fun createLogin(@Body loginCreate: LoginCreate): Response<ApiResponse>
    @POST("/login")
    suspend fun loginUser(@Body tokenRequest: TokenRequest): Response<TokenResponse>
    @GET("/advertisment/{id}")
    suspend fun getAdvertisementById(@Path("id") advertisementId: Long): Response<AdvertisementResponse>
    @PUT("/advertisment/{id}")
    suspend fun updateAdvertisement(@Path("id") advertisementId: Long, @Body updatedAdvertisement: AdvertisementUpdate): Response<ApiResponse>
    @DELETE("/advertisment/{id}")
    suspend fun deleteAdvertisement(@Path("id") advertisementId: Long): Response<ApiResponse>
    @POST("/advertisment")
    suspend fun createAdvertisement(@Body createAdvertisement: AdvertisementCreate): Response<ApiResponse>
    @GET("/advertisment")
    suspend fun getAdvertisements(
            @Query("category") categoryId: Long?,
            @Query("date") publicationDate: String?,
            @Query("countWatch") watchCount: Long?
    ): Response<List<AdvertisementResponse>>
    @GET("/media/avatar/{id}")
    suspend fun getAvatarById(@Path("id") avatarId: Long): Response<Avatar>
    @POST("/media/avatar")
    suspend fun createAvatar(@Query("user_id") userId: Long,
                             @Body createAvatar: Avatar
    ): Response<ApiResponse>
    @GET("/media/photos/{id}")
    suspend fun getPhotoById(@Path("id") photoId: Long): Response<Photo>
    @POST("/media/photos")
    suspend fun createPhoto(@Body createPhoto: Photo): Response<ApiResponse>
    @GET("/blocking/{id}")
    suspend fun getBlockingById(@Path("id") blockingId: Long): Response<BlockingResponse>
    @PUT("/blocking/{id}")
    suspend fun updateBlockingById(@Path("id") blockingId: Long, @Body updateBlocking: BlockingUpdate): Response<BlockingResponse>
    @GET("/blocking")
    suspend fun getBlockingByUserId(@Query("user_id") userId: Long): Response<List<BlockingResponse>>
    @POST("/blocking")
    suspend fun createBlocking(@Body newBlocking: BlockingCreate): Response<BlockingResponse>
    @GET("/strike/{id}")
    suspend fun getStrikeById(@Path("id") strikeId: Long): Response<StrikeResponse>
    @PUT("/strike/{id}")
    suspend fun updateStrikeById(@Path("id") strikeId: Long, @Body updateStrike: StrikeUpdate): Response<ApiResponse>
    @GET("/strike")
    suspend fun getStrikesByUserId(@Query("user_id") userId: Long): Response<List<StrikeResponse>>
    @POST("/strike")
    suspend fun createStrike(@Body createStrike: StrikeCreate): Response<ApiResponse>
    @GET("/message")
    suspend fun getMessages(
            @Query("from") fromUserId: Long,
            @Query("to") toUserId: Long
    ): Response<List<MessageResponse>>
   // @POST("/message")
   // suspend fun createMessage(@Body newMessage: NewMessage): Response<ApiResponse>
    @GET("/message/{id}")
    suspend fun getMessageById(@Path("id") messageId: Long): Response<MessageResponse>
    @GET("/favorite/advertisment")
    suspend fun getFavoriteAdvertisements(@Query("user_id") userId: Long): Response<List<AdvertisementResponse>>
    @POST("/favorite/advertisment")
    suspend fun addToFavoriteAdvertisements(@Body favoriteAdvertisementsRequest: FavoriteAdvertisementsRequest): Response<ApiResponse>
    @DELETE("/favorite/advertisment")
    suspend fun removeFromFavorites(
            @Query("user_id") userId: Long,
            @Query("ads_id") advertisementId: Long
    ): Response<ApiResponse>
    @GET("/favorite/categories")
    suspend fun getFavoriteCategories(@Query("user_id") userId: Long): Response<CategoryResponse>
    @POST("/favorite/categories")
    suspend fun addToFavoriteCategories(@Body favoriteCategoryRequest: FavoriteCategoryRequest): Response<ApiResponse>
    @DELETE("/favorite/categories")
    suspend fun removeFromFavoriteCategories(
            @Query("user_id") userId: Long,
            @Query("category_id") categoryId: Long
    ): Response<ApiResponse>
    @GET("/favorite/user")
    suspend fun getFavoriteUser(@Query("subject_id") subjectId: Long): Response<List<UserResponse>>
    @POST("/favorite/user")
    suspend fun addToFavoriteUsers(@Body favoriteUserRequest: FavoriteUserRequest): Response<ApiResponse>
    @DELETE("/favorite/user")
    suspend fun removeFromFavoriteUsers(
            @Query("subject_id") userId: Long,
            @Query("object_id") objectId: Long
    ): Response<ApiResponse>
    @GET("/booking")
    suspend fun getBookings(@Query("user_id") userId: Long): Response<List<BookingResponse>>
    @POST("/booking")
    suspend fun createBooking(@Body bookingRequest: BookingRequest): Response<BookingResponse>
    @DELETE("/booking")
    suspend fun cancelBooking(@Query("booking_id") bookingId: Long): Response<ApiResponse>
    @GET("/purchase")
    suspend fun getPurchases(@Query("user_id") userId: Long): Response<List<PurchaseResponse>>
    @POST("/purchase")
    suspend fun createPurchase(@Body purchaseRequest: PurchaseRequest): Response<ApiResponse>
    @GET("/sales")
    suspend fun getSales(@Query("user_id") userId: Long): Response<List<PurchaseResponse>>

}