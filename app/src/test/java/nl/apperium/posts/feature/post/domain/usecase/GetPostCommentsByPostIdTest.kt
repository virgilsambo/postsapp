package nl.apperium.posts.feature.post.domain.usecase

import io.mockk.clearAllMocks
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import nl.apperium.posts.feature.post.domain.model.PostComment
import nl.apperium.posts.feature.post.domain.repository.PostRepository
import org.junit.Assert
import org.junit.Before
import org.junit.Test

class GetPostCommentsByPostIdTest {
    private val mockRepository: PostRepository = mockk()
    private val mockPostComment: PostComment = mockk()
    private val postCommentList: List<PostComment> =
        listOf(mockPostComment, mockPostComment, mockPostComment)

    private val idToSearch: Int = 2

    private val useCase: GetPostCommentsByPostId =
        GetPostCommentsByPostId(postRepository = mockRepository)

    @Before
    fun setUp() {
        clearAllMocks()
    }

    private fun setupSuccessfulCall() {
        coEvery { mockRepository.getPostComments(any()) }.returns(postCommentList)
    }

    @Test
    fun `calls repository to get list of posts`() {
        // Arrange

        setupSuccessfulCall()

        // Act
        runBlocking { useCase(idToSearch) }

        // Assert
        coVerify { mockRepository.getPostComments(idToSearch) }
    }

    @Test
    fun `returns Result success containing result if call was successful`() {
        // Arrange
        setupSuccessfulCall()

        // Act
        val result = runBlocking { useCase(idToSearch) }

        // Assert
        Assert.assertEquals(Result.success(postCommentList), result)
    }

    @Test
    fun `returns Result failure containing thrown exception if call was unsuccessful`() {
        // Arrange
        val expectedException: Throwable = Exception("Something went wrong!")
        coEvery { mockRepository.getPostComments(any()) }.throws(expectedException)

        // Act
        val result = runBlocking { useCase(idToSearch) }

        // Assert
        Assert.assertEquals(Result.failure<Exception>(expectedException), result)
    }
}