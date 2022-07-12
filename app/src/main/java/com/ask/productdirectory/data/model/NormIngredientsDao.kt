package com.ask.productdirectory.data.model

import androidx.room.ColumnInfo
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity(tableName = "norm_ingredient_table")
data class NormIngredientsDao(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    @Embedded val component: NormComponentDao
) : Serializable

data class NormComponentDao(
    @ColumnInfo(name = "kcal") val kcal: Double,
    @ColumnInfo(name = "proteins") val proteins: Double,
    @ColumnInfo(name = "fats") val fats: Double,
    @ColumnInfo(name = "carbohydrates") val carbohydrates: Double,
) : Serializable