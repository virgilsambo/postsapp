package nl.apperium.posts.feature.post.data.datasource

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import nl.apperium.posts.core.data.network.services.PostService
import nl.apperium.posts.core.domain.exception.NetworkException.EmptyResponseBodyException
import nl.apperium.posts.core.domain.exception.NetworkException.RequestUnsuccessfulException
import nl.apperium.posts.feature.post.data.mapper.toPostComments
import nl.apperium.posts.feature.post.data.mapper.toPosts
import nl.apperium.posts.feature.post.domain.datasource.PostRemoteDatasource
import nl.apperium.posts.feature.post.domain.model.Post
import nl.apperium.posts.feature.post.domain.model.PostComment
import javax.inject.Inject

class PostRemoteDatasourceImpl @Inject constructor(private val postService: PostService) :
    PostRemoteDatasource {
    override suspend fun getPosts(): List<Post> {
        val response = withContext(Dispatchers.IO) {
            postService.getPosts()
        }

        if (!response.isSuccessful) {
            throw RequestUnsuccessfulException
        }

        return response.body()?.toPosts() ?: throw EmptyResponseBodyException
    }

    override suspend fun getPostComments(postId: Int): List<PostComment> {
        val response = withContext(Dispatchers.IO) {
            postService.getPostComments(postId = postId)
        }

        if (!response.isSuccessful) {
            throw RequestUnsuccessfulException
        }

        return response.body()?.toPostComments() ?: throw EmptyResponseBodyException
    }
}