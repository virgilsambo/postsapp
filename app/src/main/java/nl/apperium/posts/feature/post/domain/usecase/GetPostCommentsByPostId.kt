package nl.apperium.posts.feature.post.domain.usecase

import nl.apperium.posts.feature.post.domain.model.PostComment
import nl.apperium.posts.feature.post.domain.repository.PostRepository
import javax.inject.Inject

class GetPostCommentsByPostId @Inject constructor(private val postRepository: PostRepository) {
    suspend operator fun invoke(postId: Int): Result<List<PostComment>> {
        return runCatching {
            postRepository.getPostComments(postId = postId)
        }
    }
}