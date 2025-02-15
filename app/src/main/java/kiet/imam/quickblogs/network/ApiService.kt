package kiet.imam.quickblogs.network

import kiet.imam.quickblogs.model.BlogPost
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("posts")
    suspend fun getBlogPosts(
        @Query("per_page") perPage: Int,
        @Query("page") page: Int
    ): Response<List<BlogPost>>
}