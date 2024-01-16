package adarsh.core.data.response

sealed class UserResponse<out T> {

    class Success<out T>(val data: T) : UserResponse<T>()
    class Error(val message: String) : UserResponse<Nothing>()
    object Loading : UserResponse<Nothing>()
}
