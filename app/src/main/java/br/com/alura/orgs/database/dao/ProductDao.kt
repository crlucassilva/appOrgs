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

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun save(vararg product: Product)

    @Delete
    suspend fun remove(product: Product)

//    @Update
//    fun update(product: Product)

    @Query("SELECt * FROM Product WHERE id = :id")
    fun findById(id: Long): Flow<Product?>

    @Query("SELECT * FROM Product ORDER BY name DESC")
    suspend fun findAllOrderNameDesc(): List<Product>

    @Query("SELECT * FROM Product ORDER BY name ASC")
    suspend fun findAllOrderNameAsc(): List<Product>

    @Query("SELECT * FROM Product ORDER BY description DESC")
    suspend fun findAllOrderDescriptionDesc(): List<Product>

    @Query("SELECT * FROM Product ORDER BY description ASC")
    suspend fun findAllOrderDescriptionAsc(): List<Product>

    @Query("SELECT * FROM Product ORDER BY value DESC")
    suspend fun findAllOrderValueDesc(): List<Product>

    @Query("SELECT * FROM Product ORDER BY value ASC")
    suspend fun findAllOrderValueAsc(): List<Product>
}