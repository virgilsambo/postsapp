package nl.apperium.posts.core.domain.model

import kotlinx.serialization.Serializable

@Serializable
data class Example(
    val id: Int = -1,
    val title: String = ""
)