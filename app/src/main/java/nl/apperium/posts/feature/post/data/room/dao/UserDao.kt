package nl.apperium.posts.feature.post.data.room.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import nl.apperium.posts.feature.post.data.room.entity.UserEntity

@Dao
interface UserDao {
    @Query("SELECT * FROM userEntity")
    fun getAll(): List<UserEntity>

    @Query("SELECT * FROM userEntity WHERE uid IN (:userIds)")
    fun loadAllByIds(userIds: IntArray): List<UserEntity>

    @Query(
        "SELECT * FROM userEntity WHERE first_name LIKE :first AND " +
                "last_name LIKE :last LIMIT 1"
    )
    fun findByName(first: String, last: String): UserEntity

    @Insert
    fun insertAll(vararg userEntities: UserEntity)

    @Delete
    fun delete(userEntity: UserEntity)
}