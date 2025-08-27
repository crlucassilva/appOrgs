package br.com.alura.orgs.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import br.com.alura.orgs.model.User
import kotlinx.coroutines.flow.Flow

@Dao
interface UserDao {

    @Insert
    suspend fun save(user: User)

    @Query("SELECT * FROM User WHERE id = :userId AND password = :password")
    suspend fun authenticate(userId: String, password: String): User?

    @Query("SELECT * FROM User WHERE id = :userId")
    fun findId(userId: String): Flow<User>
}