package nl.apperium.posts.feature.post.data.repository

import nl.apperium.posts.feature.post.domain.datasource.PostRemoteDatasource
import nl.apperium.posts.feature.post.domain.model.Post
import nl.apperium.posts.feature.post.domain.model.PostComment
import nl.apperium.posts.feature.post.domain.repository.PostRepository
import javax.inject.Inject

class PostRepositoryImpl @Inject constructor(private val postRemoteDatasource: PostRemoteDatasource) :
    PostRepository {
    override suspend fun getPosts(): List<Post> = postRemoteDatasource.getPosts()

    override suspend fun getPostComments(postId: Int): List<PostComment> =
        postRemoteDatasource.getPostComments(postId = postId)
}