package nl.apperium.posts.feature.post.data.repository

import io.mockk.clearAllMocks
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import nl.apperium.posts.feature.post.domain.datasource.PostRemoteDatasource
import nl.apperium.posts.feature.post.domain.model.Post
import nl.apperium.posts.feature.post.domain.model.PostComment
import nl.apperium.posts.feature.post.domain.repository.PostRepository
import org.junit.Assert
import org.junit.Before
import org.junit.Test

class PostRepositoryImplTest {
    private val mockRemoteDataSource: PostRemoteDatasource = mockk()
    private val repository: PostRepository =
        PostRepositoryImpl(postRemoteDatasource = mockRemoteDataSource)

    @Before
    fun setUp() {
        clearAllMocks()
    }

    @Test
    fun `getPosts should get data from remote data source`() {
        // Arrange
        val expectedResult: List<Post> = mockk()
        coEvery { mockRemoteDataSource.getPosts() }.returns(expectedResult)

        // Act
        val result = runBlocking { repository.getPosts() }

        // Assert
        Assert.assertEquals(expectedResult, result)
    }

    @Test
    fun `getPostComments should get data from remote data source`() {
        // Arrange
        val expectedResult: List<PostComment> = mockk()
        coEvery { mockRemoteDataSource.getPostComments(any()) }.returns(expectedResult)

        // Act
        val result = runBlocking { repository.getPostComments(20) }

        // Assert
        Assert.assertEquals(expectedResult, result)
    }
}