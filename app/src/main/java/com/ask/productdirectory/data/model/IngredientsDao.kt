package com.ask.productdirectory.data.model

import androidx.room.ColumnInfo
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity(tableName = "ingredients_table")
data class IngredientsDao(
    @PrimaryKey(autoGenerate = true) val id: Int=0,
    @ColumnInfo(name = "name") val name: String,
    @Embedded val component: ComponentDao
): Serializable

data class ComponentDao(
    @ColumnInfo(name = "kcal")val kcal: Double,
    @ColumnInfo(name = "proteins")val proteins: Double?,
    @ColumnInfo(name = "fats")val fats: Double?,
    @ColumnInfo(name = "carbohydrates")val carbohydrates: Double?,
): Serializable