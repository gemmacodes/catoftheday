package com.switcherette.retrofit.data

data class BreedModel(
    val name: String,
    val id: String,
    val wikipedia_url: String,
    val image: ImageModel
)

data class ImageModel(
    val url: String
)