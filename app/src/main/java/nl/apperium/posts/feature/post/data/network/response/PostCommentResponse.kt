package nl.apperium.posts.feature.post.data.network.response

import com.google.gson.annotations.SerializedName

data class PostCommentResponse(
    @SerializedName("postId")
    val postId: Int,
    @SerializedName("id")
    val id: Int,
    @SerializedName("name")
    val name: String,
    @SerializedName("email")
    val email: String,
    @SerializedName("body")
    val body: String,
)
