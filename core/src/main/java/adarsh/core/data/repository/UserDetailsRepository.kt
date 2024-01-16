package adarsh.core.data.repository

import retrofit2.Response
import adarsh.core.data.api.UserService
import adarsh.core.model.User

class UserDetailsRepository(
    private val userService: UserService
) {
    suspend fun getUsersDetails(
        id: Int,
    ): Response<User> {
        return userService.getUsersDetails(id)
    }
}
