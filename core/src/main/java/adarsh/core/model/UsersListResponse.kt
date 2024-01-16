package adarsh.core.model

data class UsersListResponse(
    val limit: Int,
    val skip: Int,
    val total: Int,
    val users: List<User>
)