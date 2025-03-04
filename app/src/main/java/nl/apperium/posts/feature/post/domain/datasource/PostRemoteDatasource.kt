package nl.apperium.posts.feature.post.domain.datasource

import nl.apperium.posts.feature.post.domain.model.Post
import nl.apperium.posts.feature.post.domain.model.PostComment

interface PostRemoteDatasource {
    suspend fun getPosts(): List<Post>

    suspend fun getPostComments(postId: Int): List<PostComment>
}