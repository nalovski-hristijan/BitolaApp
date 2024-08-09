package com.hnalovski.bitolaapp.model

data class Recommendation(
    val id: Int,
    val name: Int,
    val contact: Int,
    val meals: Int,
    val features: Int,
    val image: List<RecommendationImage>,
    val about: Int,
)

data class RecommendationImage(val image: Int)
