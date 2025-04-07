package com.example.vietnamesefoodsuggestion.data

data class PredictRequest(
    val type: String,
    val flavour: String,
    val meal: String,
    val process: String,
    val vegetarian: String
)