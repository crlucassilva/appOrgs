package br.com.alura.orgs.database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import br.com.alura.orgs.model.Product
import kotlinx.coroutines.flow.Flow

@Dao
interface ProductDao {

    @Query("SELECT * FROM Product")
    fun findAll(): Flow<List<Product>>

    @Query("SELECT * FROM Product WHERE userId = :userId")
    fun findAllUser(userId: String): Flow<List<Product>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun save(vararg product: Product)

    @Delete
    suspend fun remove(product: Product)

    @Query("SELECt * FROM Product WHERE id = :id")
    fun findById(id: Long): Flow<Product?>

    @Query("SELECT * FROM Product WHERE userId = :userId ORDER BY name DESC")
    suspend fun findAllOrderNameDesc(userId: String): List<Product>

    @Query("SELECT * FROM Product WHERE userId = :userId ORDER BY name ASC")
    suspend fun findAllOrderNameAsc(userId: String): List<Product>

    @Query("SELECT * FROM Product WHERE userId = :userId ORDER BY description DESC")
    suspend fun findAllOrderDescriptionDesc(userId: String): List<Product>

    @Query("SELECT * FROM Product WHERE userId = :userId ORDER BY description ASC")
    suspend fun findAllOrderDescriptionAsc(userId: String): List<Product>

    @Query("SELECT * FROM Product WHERE userId = :userId ORDER BY value DESC")
    suspend fun findAllOrderValueDesc(userId: String): List<Product>

    @Query("SELECT * FROM Product WHERE userId = :userId ORDER BY value ASC")
    suspend fun findAllOrderValueAsc(userId: String): List<Product>
}