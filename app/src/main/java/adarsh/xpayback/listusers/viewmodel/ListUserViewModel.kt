package adarsh.xpayback.listusers.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import adarsh.core.data.repository.ListUserRepository
import adarsh.core.data.response.UserResponse
import adarsh.core.model.UsersListResponse

class ListUserViewModel(
    private val listUserRepository: ListUserRepository
) : ViewModel() {

    private val _usersList = MutableLiveData<UserResponse<UsersListResponse>>()
    val usersList: LiveData<UserResponse<UsersListResponse>> = _usersList

    fun getUserList() {
        _usersList.value = UserResponse.Loading
        viewModelScope.launch {
            try {
                val response = listUserRepository.getUsers()
                if (response.isSuccessful) {
                    _usersList.value = UserResponse.Success(response.body()!!)
                } else {
                    _usersList.value = UserResponse.Error(response.message())
                }
            } catch (e: Exception) {
                _usersList.value = UserResponse.Error(e.localizedMessage ?: "An error occurred")
            }
        }
    }
}
