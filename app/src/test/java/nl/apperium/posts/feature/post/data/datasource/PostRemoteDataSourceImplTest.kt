package nl.apperium.posts.feature.post.data.datasource

import io.mockk.clearAllMocks
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import nl.apperium.posts.core.data.network.services.PostService
import nl.apperium.posts.core.domain.exception.NetworkException
import nl.apperium.posts.feature.post.data.network.response.PostCommentResponse
import nl.apperium.posts.feature.post.data.network.response.PostResponse
import nl.apperium.posts.feature.post.domain.datasource.PostRemoteDatasource
import nl.apperium.posts.feature.post.domain.model.Post
import nl.apperium.posts.feature.post.domain.model.PostComment
import okhttp3.ResponseBody.Companion.toResponseBody
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import retrofit2.Response

class PostRemoteDataSourceImplTest {
    private val mockPostService: PostService = mockk()
    private val remoteDatasourceImpl: PostRemoteDatasource =
        PostRemoteDatasourceImpl(postService = mockPostService)

    @Before
    fun setUp() {
        clearAllMocks()
    }

    //region getPosts
    @Test
    fun `getPosts returns list of Posts if call was successful`() {
        // Arrange
        val postUserId = 10
        val postId = 20
        val postTitle = "title"
        val postBody = "body"

        val postResponse = PostResponse(postUserId, postId, postTitle, postBody)
        val postResponseList: List<PostResponse> =
            listOf(postResponse, postResponse, postResponse)

        val post = Post(postUserId, postId, postTitle, postBody)
        val postList: List<Post> = listOf(post, post, post)

        val successfulResponse = Response.success(postResponseList)
        coEvery { mockPostService.getPosts() }.returns(successfulResponse)

        // Act
        val result = runBlocking { remoteDatasourceImpl.getPosts() }

        // Assert
        Assert.assertEquals(postList, result)
    }

    @Test(expected = NetworkException.RequestUnsuccessfulException::class)
    fun `getPosts should throw RequestUnsuccessfulException if response was not successful`() {
        // Arrange
        val failedResponse = Response.error<List<PostResponse>>(404, """""".toResponseBody())
        coEvery { mockPostService.getPosts() }.returns(failedResponse)

        // Act
        runBlocking { remoteDatasourceImpl.getPosts() }
    }

    @Test(expected = NetworkException.EmptyResponseBodyException::class)
    fun `getPosts should throw EmptyResponseBodyException if response successful but body couldn't be parsed`() {
        // Arrange
        val failedResponse = Response.success<List<PostResponse>>(null)
        coEvery { mockPostService.getPosts() }.returns(failedResponse)

        // Act
        runBlocking { remoteDatasourceImpl.getPosts() }
    }
    //endregion

    //region getPostComments
    @Test
    fun `getPostComments returns list of PostComments if call was successful`() {
        // Arrange
        val postId = 1
        val id = 2
        val name = "post comment name"
        val email = "post comment email"
        val body = "post comment body"
        val postCommentResponse = PostCommentResponse(postId, id, name, email, body)
        val postCommentResponseList: List<PostCommentResponse> =
            listOf(postCommentResponse, postCommentResponse, postCommentResponse)

        val postComment = PostComment(postId, id, name, email, body)
        val postCommentList: List<PostComment> = listOf(postComment, postComment, postComment)

        val successfulResponse = Response.success(postCommentResponseList)
        coEvery { mockPostService.getPostComments(any()) }.returns(successfulResponse)

        // Act
        val idToSearch = 8
        val result = runBlocking { remoteDatasourceImpl.getPostComments(idToSearch) }

        // Assert
        Assert.assertEquals(postCommentList, result)
        coVerify { mockPostService.getPostComments(idToSearch) }
    }

    @Test(expected = NetworkException.RequestUnsuccessfulException::class)
    fun `getPostComments should throw RequestUnsuccessfulException if response was not successful`() {
        // Arrange
        val failedResponse = Response.error<List<PostCommentResponse>>(404, """""".toResponseBody())
        coEvery { mockPostService.getPostComments(any()) }.returns(failedResponse)

        // Act
        runBlocking { remoteDatasourceImpl.getPostComments(2) }
    }

    @Test(expected = NetworkException.EmptyResponseBodyException::class)
    fun `getPostComments should throw EmptyResponseBodyException if response successful but body couldn't be parsed`() {
        // Arrange
        val failedResponse = Response.success<List<PostCommentResponse>>(null)
        coEvery { mockPostService.getPostComments(any()) }.returns(failedResponse)

        // Act
        runBlocking { remoteDatasourceImpl.getPostComments(2) }
    }
    //endregion
}