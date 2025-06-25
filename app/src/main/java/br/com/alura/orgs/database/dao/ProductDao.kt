package br.com.alura.orgs.database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import br.com.alura.orgs.model.Product

@Dao
interface ProductDao {

    @Query("SELECT * FROM Product")
    fun findAll(): List<Product>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun save(vararg product: Product)

    @Delete
    fun remove(product: Product)

//    @Update
//    fun update(product: Product)

    @Query("SELECt * FROM Product WHERE id = :id")
    fun findById(id: Long): Product?
}