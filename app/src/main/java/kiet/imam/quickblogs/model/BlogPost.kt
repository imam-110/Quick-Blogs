package kiet.imam.quickblogs.model

data class BlogPost(
    val id: Int,
    val date: String,
    val link: String,
    val title: RenderedText,
    val content: RenderedText,
    val guid: RenderedGuid,
    val slug: String,
) {
    data class RenderedText(val rendered: String)
    data class RenderedGuid(val rendered: String)
}