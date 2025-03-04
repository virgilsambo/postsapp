package nl.apperium.posts.feature.post.domain.usecase

import io.mockk.clearAllMocks
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import nl.apperium.posts.feature.post.domain.model.Post
import nl.apperium.posts.feature.post.domain.repository.PostRepository
import org.junit.Assert
import org.junit.Before
import org.junit.Test

class GetPostsUseCaseTest {
    private val mockRepository: PostRepository = mockk()
    private val mockPost: Post = mockk()
    private val postList: List<Post> = listOf(mockPost, mockPost, mockPost)

    private val useCase: GetPostsUseCase = GetPostsUseCase(postRepository = mockRepository)

    @Before
    fun setUp() {
        clearAllMocks()
    }

    private fun setupSuccessfulCall() {
        coEvery { mockRepository.getPosts() }.returns(postList)
    }

    @Test
    fun `calls repository to get list of posts`() {
        // Arrange
        setupSuccessfulCall()

        // Act
        runBlocking { useCase() }

        // Assert
        coVerify { mockRepository.getPosts() }
    }

    @Test
    fun `returns Result success containing result if call was successful`() {
        // Arrange
        setupSuccessfulCall()

        // Act
        val result = runBlocking { useCase() }

        // Assert
        Assert.assertEquals(Result.success(postList), result)
    }

    @Test
    fun `returns Result failure containing thrown exception if call was unsuccessful`() {
        // Arrange
        val expectedException: Throwable = Exception("Something went wrong!")
        coEvery { mockRepository.getPosts() }.throws(expectedException)

        // Act
        val result = runBlocking { useCase() }

        // Assert
        Assert.assertEquals(Result.failure<Exception>(expectedException), result)
    }
}