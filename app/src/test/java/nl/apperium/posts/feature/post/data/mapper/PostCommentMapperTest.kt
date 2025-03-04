package nl.apperium.posts.feature.post.data.mapper

import io.mockk.clearAllMocks
import io.mockk.coVerify
import io.mockk.mockkStatic
import nl.apperium.posts.feature.post.data.network.response.PostCommentResponse
import nl.apperium.posts.feature.post.domain.model.PostComment
import org.junit.Assert
import org.junit.Before
import org.junit.Test

class PostCommentMapperTest {
    // TODO: Handle random params using faker?
    private val postId: Int = 20
    private val id: Int = 30
    private val name: String = "test name"
    private val email: String = "test email"
    private val body: String = "test body"

    private val postCommentResponse: PostCommentResponse =
        PostCommentResponse(postId, id, name, email, body)
    private val postComment: PostComment = PostComment(postId, id, name, email, body)

    private val postCommentResponseList: List<PostCommentResponse> =
        listOf(postCommentResponse, postCommentResponse, postCommentResponse)
    private val postCommentList: List<PostComment> = listOf(postComment, postComment, postComment)

    @Before
    fun setUp() {
        clearAllMocks()
        mockkStatic("nl.mylermedia.defaultprojectcompose.feature.post.data.mapper.PostCommentMapperKt")
    }

    @Test
    fun `PostCommentResponse should be properly mapped to a PostComment`() {
        // Act
        val result = postCommentResponse.toPostComment()

        // Assert
        Assert.assertEquals(postComment, result)

    }

    @Test
    fun `List of PostCommentResponse should be properly mapped to a List of PostComments`() {
        // Act
        val result = postCommentResponseList.toPostComments()

        // Assert
        Assert.assertEquals(postCommentList, result)
    }

    @Test
    fun `List of PostCommentResponse should be mapped using toPostComment function`() {
        // Act
        postCommentResponseList.toPostComments()

        // Assert
        coVerify { any<PostCommentResponse>().toPostComment() }
    }
}