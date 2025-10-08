package ir.arash.altafi.sample.ui.presentation.testDetail

import androidx.lifecycle.viewModelScope
import ir.arash.altafi.sample.data.model.TestDetailEntity
import ir.arash.altafi.sample.data.repository.TestRepository
import ir.arash.altafi.sample.utils.base.ApiState
import ir.arash.altafi.sample.utils.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TestDetailViewModel @Inject constructor(
    private val repository: TestRepository
) : BaseViewModel<TestDetailEntity>() {

    fun loadUserDetail(id: String) {
        _apiState.value = ApiState.Loading
        viewModelScope.launch {
            try {
                val user = repository.getUserDetail(id)
                if (user != null) {
                    _apiState.value = ApiState.Success(user)
                } else {
                    _apiState.value = ApiState.Error("User not found")
                }
            } catch (e: Exception) {
                _apiState.value = ApiState.Error(e.localizedMessage ?: "Error loading detail")
            }
        }
    }
}