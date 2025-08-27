package br.com.alura.orgs.database.dao

import androidx.room.Dao
import androidx.room.Insert
import br.com.alura.orgs.model.User

@Dao
interface UserDao {

    @Insert
    suspend fun save(user: User)
}