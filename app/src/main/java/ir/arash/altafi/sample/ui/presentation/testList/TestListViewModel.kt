package ir.arash.altafi.sample.ui.presentation.testList

import androidx.lifecycle.viewModelScope
import ir.arash.altafi.sample.data.model.TestDetailEntity
import ir.arash.altafi.sample.data.repository.TestRepository
import ir.arash.altafi.sample.utils.base.ApiState
import ir.arash.altafi.sample.utils.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TestListViewModel @Inject constructor(
    private val repository: TestRepository
) : BaseViewModel<List<TestDetailEntity>>() {

    init {
        loadUsers()
    }

    fun loadUsers() {
        _apiState.value = ApiState.Loading
        viewModelScope.launch {
            try {
                val users = repository.getUserList() ?: emptyList()
                _apiState.value = ApiState.Success(users)
            } catch (e: Exception) {
                _apiState.value = ApiState.Error(e.localizedMessage ?: "Error loading users")
            }
        }
    }
}