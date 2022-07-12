package com.ask.productdirectory.data.datasource

import com.ask.productdirectory.data.converter.toIngredients
import com.ask.productdirectory.data.converter.toNormIngredient
import com.ask.productdirectory.data.database.AppDatabase
import com.ask.productdirectory.data.model.ComponentDto
import com.ask.productdirectory.data.model.IngredientsDto
import com.ask.productdirectory.domain.entity.Ingredient
import com.ask.productdirectory.domain.entity.NormIngredient
import java.lang.NullPointerException
import javax.inject.Inject

class IngredientsDataSourceImpl @Inject constructor(
    private val appDatabase: AppDatabase
) : IngredientsDataSource {

    private val db = appDatabase.getDao()

    override suspend fun getIngredient(name: String, source: Boolean): List<Ingredient> =
        if (source) {
            val listIngredient = db.searchIngredient("%$name%")
            listIngredient?.map { it.toIngredients() } ?: emptyList()
        } else listOf(
            IngredientsDto(
                0, "Абрикос", listOf(
                    ComponentDto(name = "Калорийность", value = 45.0),
                    ComponentDto(name = "Белки", value = 12.8),
                    ComponentDto(name = "Жиры", value = 17.9),
                    ComponentDto(name = "Углеводы", value = 20.0)
                )
            ).toIngredients(),
            IngredientsDto(
                0, "Абрикос сущенный", listOf(
                    ComponentDto(name = "Калорийность", value = 995.0),
                    ComponentDto(name = "Белки", value = 12.8),
                    ComponentDto(name = "Жиры", value = 17.9),
                    ComponentDto(name = "Углеводы", value = 20.0)
                )
            ).toIngredients(),
            IngredientsDto(
                0, "Арбуз", listOf(
                    ComponentDto(name = "Калорийность", value = 145.0),
                    ComponentDto(name = "Белки", value = 12.8),
                    ComponentDto(name = "Жиры", value = 17.9),
                    ComponentDto(name = "Углеводы", value = 20.0)
                )
            ).toIngredients(),
            IngredientsDto(
                0, "Помидор", listOf(
                    ComponentDto(name = "Калорийность", value = 70.0),
                    ComponentDto(name = "Белки", value = 12.8),
                    ComponentDto(name = "Жиры", value = 17.9),
                    ComponentDto(name = "Углеводы", value = 20.0)
                )
            ).toIngredients(),
        )

    override suspend fun readIngredient(id: Int): Ingredient {
        val ingredient = db.readIngredients(id)
        if (ingredient != null) {
            return ingredient.toIngredients()
        } else throw NullPointerException("Нет данных")
    }

    override suspend fun readNormIngredient(): NormIngredient {
        val normIngredient = db.normIngredient()
        if (normIngredient != null) {
            return normIngredient.toNormIngredient()
        } else throw NullPointerException("Нет данных")
    }
}

