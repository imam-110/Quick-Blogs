package kiet.imam.quickblogs

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import dagger.hilt.android.AndroidEntryPoint
import kiet.imam.quickblogs.presentation.BlogDetailScreen
import kiet.imam.quickblogs.presentation.BlogListScreen
import kiet.imam.quickblogs.ui.theme.QuickBlogsTheme


@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            QuickBlogsTheme {
                val navController = rememberNavController()

                Scaffold(
                    modifier = Modifier.fillMaxSize(),
                    topBar = { TopAppBar(title = { Text("QuickBlogs") }) }
                ) { innerPadding ->
                    NavHost(
                        navController = navController,
                        startDestination = "blogList",
                        modifier = Modifier.padding(innerPadding)
                    ) {
                        composable("blogList") {
                            BlogListScreen { blogUrl ->
                                navController.navigate("blogDetail?url=$blogUrl")
                            }
                        }
                        composable("blogDetail?url={url}", arguments = listOf(navArgument("url") { defaultValue=""})
                            ) { backStackEntry ->
                            val url = backStackEntry.arguments?.getString("url") ?: ""
                            BlogDetailScreen(url = url)
                        }
                    }
                }
            }
        }
    }
}

