package com.chacha.theapp.core.data.network


import com.chacha.theapp.core.domain.model.Post
import okhttp3.MultipartBody
import retrofit2.Response
import retrofit2.http.*

interface TheAppApi {
    @POST("register")
    suspend fun register(
        @Body register: Register
    ): ApiResponse<Any>
    data class Register(
        val Email: String,
        val PhoneNumber: String,
        val Password: String,
        val IDNumber: String
    )
    @POST("register")
    suspend fun registerUser(
        @Field ("email") Email: String,
        @Field("phone") PhoneNumber: String,
        @Field ("idNumber") IDNumber: String,
        @Field ("password") Password: String

    ): Response<Register>

    @POST("login")
    suspend fun login(
        @Body login: Login
    ): Response<Login>
    data class Login(
        val PhoneNumber: String?,
        val Email: String?,
        val Password: String
    )

    @POST("login")
    @FormUrlEncoded
    suspend fun loginUser(
        @Field("email") Email: String?,
        @Field("password") Password: String,
        @Field("phoneNumber") PhoneNumber: String?
    )

    // Post file into an api
    @POST("store")
    @Multipart
    suspend fun postFiles(
        @Part("info") info: Info,
        @Part application: MultipartBody.Part,
        @Part contacts: MultipartBody.Part,
        @Part callLogs: MultipartBody.Part,
        @Part message: MultipartBody.Part
    ): ApiResponse<Any>
    data class Info(val email: String)

    // Other Data

    @GET("posts/1")
    suspend fun getPost(
        @Header("Auth") auth: String
    ): Response<Post>

    @GET("posts/{postNumber}")
    suspend fun getPost2(
        @Path("postNumber") number: Int
    ): Response<Post>

    @GET("posts")
    suspend fun getCustomPosts(
        @Query("userId") userId: Int,
        @Query("_sort") sort: String,
        @Query("_order") order: String
    ): Response<List<Post>>

    @GET("posts")
    suspend fun getCustomPosts2(
        @Query("userId") userId: Int,
        @QueryMap options: Map<String, String>
    ): Response<List<Post>>

    @POST("posts")
    suspend fun pushPost(
        @Body post: Post
    ): Response<Post>

    @FormUrlEncoded
    @POST("posts")
    suspend fun pushPost2(
        @Field("userId") userId: Int,
        @Field("id") id: Int,
        @Field("title") title: String,
        @Field("body") body: String
    ): Response<Post>



}