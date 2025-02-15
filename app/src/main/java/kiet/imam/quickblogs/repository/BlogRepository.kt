package kiet.imam.quickblogs.repository

import kiet.imam.quickblogs.model.BlogPost
import kiet.imam.quickblogs.network.ApiService
import retrofit2.Response
import javax.inject.Inject

class BlogRepository @Inject constructor(private val apiService: ApiService) {
    suspend fun getBlogs(perPage: Int, page: Int): Response<List<BlogPost>> {
        return apiService.getBlogPosts(perPage, page)
    }
}