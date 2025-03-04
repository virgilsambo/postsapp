package nl.apperium.posts.core.data.network.services

import nl.apperium.posts.feature.post.data.network.response.PostCommentResponse
import nl.apperium.posts.feature.post.data.network.response.PostResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface PostService {
    @GET("/posts/{id}")
    suspend fun getPost(
        @Path("id") id: Int
    ): Response<PostResponse>

    @GET("/posts")
    suspend fun getPosts(): Response<List<PostResponse>>

    @GET("/posts/{id}/comments")
    suspend fun getPostComments(
        @Path("id") postId: Int
    ): Response<List<PostCommentResponse>>
}