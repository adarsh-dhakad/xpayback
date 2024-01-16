package adarsh.xpayback.userdeatils.viewmodel

import adarsh.core.data.repository.UserDetailsRepository
import adarsh.core.data.response.UserResponse
import adarsh.core.model.User
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class UserDetailsViewModel ( private val userDetailsRepository: UserDetailsRepository): ViewModel(){
    private val _userDetailsResponse = MutableLiveData<UserResponse<User>>()
    val userDetailsResponse: LiveData<UserResponse<User>> = _userDetailsResponse

    fun getUserDetails(
        id: Int,
    ) {
        _userDetailsResponse.value = UserResponse.Loading
        viewModelScope.launch {
            try {
                val response = userDetailsRepository.getUsersDetails(id)
                if (response.isSuccessful) {
                    _userDetailsResponse.value = UserResponse.Success(response.body()!!)
                } else {
                    _userDetailsResponse.value = UserResponse.Error(response.message())
                }
            } catch (e: Exception) {
                _userDetailsResponse.value = UserResponse.Error(e.localizedMessage ?: "An error occurred")
            }
        }
    }
}