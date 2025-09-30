package ir.arash.altafi.sample.data.api

import ir.arash.altafi.sample.data.model.CelebrityResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface CelebrityService {

    @GET("test_paging/test_paging2.php")
    suspend fun getCelebrities(
        @Query("page_number") pageNumber: Int,
        @Query("page_size") pageSize: Int
    ): Response<List<CelebrityResponse>>

}