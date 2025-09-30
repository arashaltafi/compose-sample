package ir.arash.altafi.sample.data.repository

import ir.arash.altafi.sample.data.api.UserService
import com.arash.altafi.mvisample.utils.base.BaseRepository
import javax.inject.Inject

class UserRepository @Inject constructor(
    private val service: UserService,
) : BaseRepository() {

    fun getUsers(pageNumber: Int, pageSize: Int) = callApi {
        service.getUsersPaging(pageNumber, pageSize)
    }

}

