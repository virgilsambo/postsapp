package nl.apperium.posts.feature.post.domain.repository

import nl.apperium.posts.feature.post.domain.model.Post
import nl.apperium.posts.feature.post.domain.model.PostComment

interface PostRepository {
    suspend fun getPosts(): List<Post>

    suspend fun getPostComments(postId: Int): List<PostComment>
}