package com.ask.productdirectory.domain.entity

import java.io.Serializable

data class Ingredient(
    val id: Int,
    val name: String,
    val component: List<Component>
) : Serializable

data class Component(
    val name: String,
    val value: Double
) : Serializable

data class NormIngredient(
    val component: List<NormComponent>
) : Serializable

data class NormComponent(
    val name: String,
    val value: Double
) : Serializable