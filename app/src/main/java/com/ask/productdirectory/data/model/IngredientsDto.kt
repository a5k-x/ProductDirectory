package com.ask.productdirectory.data.model

data class IngredientsDto(
    val id: Int,
    val name: String,
    val component: List<ComponentDto>
)

data class ComponentDto(
    val name: String,
    val value: Double
)
