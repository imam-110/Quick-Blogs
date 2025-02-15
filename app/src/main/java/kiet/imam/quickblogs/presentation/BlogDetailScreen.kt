package kiet.imam.quickblogs.presentation

import android.annotation.SuppressLint
import android.util.Log
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.ui.Modifier
import androidx.compose.ui.viewinterop.AndroidView
import androidx.compose.runtime.Composable

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BlogDetailScreen(url : String){
    Log.d("BlogDetailScreen", "Received URL: $url")


    Scaffold(
        topBar = { TopAppBar(title = { Text("Blog Detail") }) }
    ) { paddingValues ->
        Box(modifier = Modifier.padding(paddingValues)) {
            if (url.isNotEmpty()) {
                AndroidView(
                    modifier = Modifier.fillMaxSize(),
                    factory = { context ->
                        WebView(context).apply {
                            webViewClient = WebViewClient()
                            loadUrl(url)
                        }
                    }
                )
            } else {
                Text("Invalid URL", modifier = Modifier.fillMaxSize())
            }
        }
    }
}
