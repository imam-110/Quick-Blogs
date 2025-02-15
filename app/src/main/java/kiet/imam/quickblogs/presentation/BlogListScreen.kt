package kiet.imam.quickblogs.presentation

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import kiet.imam.quickblogs.viewmodel.BlogViewModel


@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BlogListScreen(viewModel: BlogViewModel = hiltViewModel(), onPostClick: (String) -> Unit) {
    val blogs by viewModel.blogPosts.collectAsState()
    val errorMessage by viewModel.errorMessage.collectAsState()
    Log.d("BlogListScreen", "Total blogs: ${blogs.size}")

    LaunchedEffect(Unit) {
        viewModel.fetchBlogs()
    }

    Scaffold(
        topBar = {
            TopAppBar(title = { Text("Blog List") })
        }
    ) { paddingValues ->
        Box(modifier = Modifier
            .fillMaxSize()
            .padding(paddingValues)) {
            if (!errorMessage.isNullOrEmpty()) {
                Text(text = errorMessage!!, modifier = Modifier.padding(16.dp))
            } else {
                LazyColumn(contentPadding = PaddingValues(16.dp)) {
                    items(blogs) { post ->
                        Card(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(vertical = 8.dp)
                                .clickable { onPostClick(post.link) },
                            shape = MaterialTheme.shapes.medium
                        ) {
                            Column(modifier = Modifier.padding(16.dp)) {
                                Text(
                                    text = post.title.rendered,
                                    style = MaterialTheme.typography.bodyLarge
                                )
                                Spacer(modifier = Modifier.height(4.dp))
                                Text(
                                    text = post.date,
                                    style = MaterialTheme.typography.bodySmall
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}

