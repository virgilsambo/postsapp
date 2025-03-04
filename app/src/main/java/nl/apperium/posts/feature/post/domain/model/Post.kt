package nl.apperium.posts.feature.post.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Post(
    val userId: Int = Int.MAX_VALUE,
    val id: Int = Int.MAX_VALUE,
    val title: String = "Test title",
    val body: String = "Test body",
) : Parcelable
