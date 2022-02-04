package com.switcherette.retrofit.data

data class CatModel(
    val breeds: List<Breed>,
    val height: Int,
    val id: String,
    val url: String,
    val width: Int
)

data class Breed(
    val description: String,
    val id: String,
    val reference_image_id: String,
    val wikipedia_url: String
)

