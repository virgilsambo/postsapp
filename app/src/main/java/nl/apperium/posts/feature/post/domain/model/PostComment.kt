package nl.apperium.posts.feature.post.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class PostComment(
    val postId: Int = Int.MAX_VALUE,
    val id: Int = Int.MAX_VALUE,
    val name: String = "Test name",
    val email: String = "Test email",
    val body: String = "Test body",
) : Parcelable
