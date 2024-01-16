package adarsh.core.data.api

import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Response
import retrofit2.http.*
import adarsh.core.model.User
import adarsh.core.model.UsersListResponse

interface UserService {
    @GET("users?limit=10&skip=0")
    suspend fun getUserList(): Response<UsersListResponse>

    @GET("users/{id}")
    suspend fun getUsersDetails(
        @Path("id") id: Int
    ): Response<User>
}
