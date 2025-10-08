package ir.arash.altafi.sample.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import ir.arash.altafi.sample.data.api.PagingService
import ir.arash.altafi.sample.data.dataSource.UsersPagingSource
import ir.arash.altafi.sample.data.model.UserResponse
import ir.arash.altafi.sample.utils.base.BaseRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class PagingRepository @Inject constructor(
    private val service: PagingService,
) : BaseRepository() {

    fun getUsersPagingData(pageSize: Int): Flow<PagingData<UserResponse>> {
        return Pager(
            config = PagingConfig(
                pageSize = pageSize,
                enablePlaceholders = false
            ),
            pagingSourceFactory = { UsersPagingSource(service, pageSize) }
        ).flow
    }

}