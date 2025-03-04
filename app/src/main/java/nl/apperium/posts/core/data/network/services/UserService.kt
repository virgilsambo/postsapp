package nl.apperium.posts.core.data.network.services

import nl.apperium.posts.feature.post.data.room.entity.UserEntity
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface UserService {
    @GET("/users/{id}")
    suspend fun getUser(
        @Path("id") id: Int
    ): Response<UserEntity>

    @GET("/users")
    suspend fun getUsers(): Response<List<UserEntity>>

    @POST("/user")
    suspend fun createUser(
        @Body userEntity: UserEntity
    ): Response<UserEntity>
}