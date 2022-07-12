package com.ask.productdirectory.data.database

import androidx.room.Dao
import androidx.room.Query
import com.ask.productdirectory.data.model.IngredientsDao
import com.ask.productdirectory.data.model.NormIngredientsDao


@Dao
interface Dao {

    @Query("SELECT * FROM ingredients_table where name like :name LIMIT 10")
    suspend fun searchIngredient(name: String): List<IngredientsDao>?

    @Query("SELECT * FROM ingredients_table WHERE id=:id")
    suspend fun readIngredients(id: Int): IngredientsDao?

    @Query("DELETE FROM ingredients_table")
    suspend fun deleteAll()

    @Query("SELECT * FROM norm_ingredient_table")
    suspend fun normIngredient(): NormIngredientsDao?
}
