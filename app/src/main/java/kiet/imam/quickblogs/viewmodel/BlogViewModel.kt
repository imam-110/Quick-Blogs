package kiet.imam.quickblogs.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kiet.imam.quickblogs.model.BlogPost
import kiet.imam.quickblogs.repository.BlogRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import retrofit2.Response
import javax.inject.Inject


@HiltViewModel
class BlogViewModel @Inject constructor(private val repository: BlogRepository) : ViewModel() {

    private val _blogPosts = MutableStateFlow<List<BlogPost>>(emptyList())
    val blogPosts: StateFlow<List<BlogPost>> get() = _blogPosts

    private val _errorMessage = MutableStateFlow<String?>(null)
    val errorMessage: StateFlow<String?> get() = _errorMessage

    fun fetchBlogs(perPage: Int = 10, page: Int = 1) {
        viewModelScope.launch {
            try {
                val response: Response<List<BlogPost>> = repository.getBlogs(perPage, page)
                if (response.isSuccessful) {
                    _blogPosts.value = response.body() ?: emptyList()
                } else {
                    _errorMessage.value = "Error: ${response.code()} - ${response.message()}"
                }
            } catch (e: Exception) {
                _errorMessage.value = "Exception: ${e.localizedMessage}"
            }
        }
    }
}