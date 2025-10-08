package ir.arash.altafi.sample.data.api

import ir.arash.altafi.sample.data.model.TestDetailEntity
import ir.arash.altafi.sample.utils.base.BaseService
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface TestService: BaseService {

    @GET("test_paging/test_paging.php")
    suspend fun getUsersPaging(
        @Query("page_number") pageNumber: Int,
        @Query("page_size") pageSize: Int
    ): Response<List<TestDetailEntity>>

    @GET("test_paging/test.php")
    suspend fun getUsers(): Response<List<TestDetailEntity>>

    @GET("test_paging/test_detail.php")
    suspend fun getUserDetail(
        @Query("id") id: String,
    ): Response<TestDetailEntity>

}