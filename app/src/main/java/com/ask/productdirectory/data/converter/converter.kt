package com.ask.productdirectory.data.converter

import com.ask.productdirectory.domain.entity.Component
import com.ask.productdirectory.domain.entity.Ingredient
import com.ask.productdirectory.domain.entity.NormComponent
import com.ask.productdirectory.domain.entity.NormIngredient
import com.ask.productdirectory.data.model.*

fun IngredientsDto.toIngredients() = Ingredient(
    id = this.id,
    name = this.name,
    component = this.component.map { it.toComponent() }
)

fun ComponentDto.toComponent() = Component(
    name = this.name,
    value = this.value
)

fun IngredientsDao.toIngredients() = Ingredient(
    id = this.id,
    name = this.name,
    component = this.component.toComponent()
)

fun ComponentDao.toComponent() = listOf(
    Component(name = "Калорийность", value = this.kcal),
    Component(name = "Белки", value = this.proteins ?: 0.0),
    Component(name = "Жиры", value = this.fats ?: 0.0),
    Component(name = "Углеводы", value = this.carbohydrates ?: 0.0)
)

fun NormIngredientsDao.toNormIngredient(): NormIngredient {
    return NormIngredient(
        component = listOf(
            NormComponent(name = "Калорийность", value = this.component.kcal),
            NormComponent(name = "Белки", value = this.component.proteins),
            NormComponent(name = "Жиры", value = this.component.fats),
            NormComponent(name = "Углеводы", value = this.component.carbohydrates)
        )
    )
}
