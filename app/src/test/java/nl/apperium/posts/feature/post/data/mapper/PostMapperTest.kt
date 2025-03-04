package nl.apperium.posts.feature.post.data.mapper

import io.mockk.clearAllMocks
import io.mockk.coVerify
import io.mockk.mockkStatic
import nl.apperium.posts.feature.post.data.network.response.PostResponse
import nl.apperium.posts.feature.post.domain.model.Post
import org.junit.Assert
import org.junit.Before
import org.junit.Test

class PostMapperTest {
    private val postUserId: Int = 1
    private val postId: Int = 2
    private val postTitle: String = "Post title"
    private val postBody: String = "Post body"

    private val postResponse: PostResponse = PostResponse(postUserId, postId, postTitle, postBody)
    private val post: Post = Post(postUserId, postId, postTitle, postBody)

    private val postResponseList: List<PostResponse> =
        listOf(postResponse, postResponse, postResponse)
    private val postList: List<Post> = listOf(post, post, post)

    @Before
    fun setUp() {
        clearAllMocks()
        mockkStatic("nl.mylermedia.defaultprojectcompose.feature.post.data.mapper.PostMapperKt")
    }

    @Test
    fun `PostResponse should be properly mapped to Post`() {
        // Act
        val result = postResponse.toPost()

        // Assert
        Assert.assertEquals(post, result)
    }

    @Test
    fun `List of PostResponse should be properly mapped to a List of Post`() {
        // Act
        val result = postResponseList.toPosts()

        // Assert
        Assert.assertEquals(postList, result)
    }

    @Test
    fun `List of PostResponse should be mapped using toPost function`() {
        // Act
        postResponseList.toPosts()

        // Assert
        coVerify { any<PostResponse>().toPost() }
    }
}