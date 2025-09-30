package ir.arash.altafi.sample.ui.presentation.user

import ir.arash.altafi.sample.data.model.UserResponse

sealed class UserState {
    object Idle : UserState()
    object Loading : UserState()
    data class Success(val users: List<UserResponse>) : UserState()
    data class Error(val message: String) : UserState()
}