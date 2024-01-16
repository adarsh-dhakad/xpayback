package adarsh.core.data.repository

import retrofit2.Response
import adarsh.core.data.api.UserService
import adarsh.core.model.UsersListResponse

class ListUserRepository(
    private val userService: UserService
) {
    suspend fun getUsers(): Response<UsersListResponse> {
        return userService.getUserList()
    }
}
