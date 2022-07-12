package com.ask.productdirectory.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.ask.productdirectory.data.model.IngredientsDao
import com.ask.productdirectory.data.model.NormIngredientsDao


@Database(entities = [IngredientsDao::class, NormIngredientsDao::class], version = 1, exportSchema = true)
abstract class AppDatabase : RoomDatabase() {
    abstract fun getDao(): Dao

    companion object {

        private var instance: AppDatabase? = null
        private const val NAME_DB = "ingredients_table"

        fun getDatabase(context: Context): AppDatabase =
            instance ?: synchronized(this) {
                instance ?: buildDatabase(context).also {
                    instance = it
                }
            }

        private fun buildDatabase(applicationContext: Context) =
            Room.databaseBuilder(applicationContext, AppDatabase::class.java, NAME_DB)
                .build()
    }
}

