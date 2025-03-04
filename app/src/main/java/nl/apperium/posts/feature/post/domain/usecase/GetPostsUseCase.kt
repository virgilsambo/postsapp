package nl.apperium.posts.feature.post.domain.usecase

import nl.apperium.posts.feature.post.domain.model.Post
import nl.apperium.posts.feature.post.domain.repository.PostRepository
import javax.inject.Inject

class GetPostsUseCase @Inject constructor(private val postRepository: PostRepository) {
    suspend operator fun invoke(): Result<List<Post>> {
        return runCatching {
            postRepository.getPosts()
        }
    }
}