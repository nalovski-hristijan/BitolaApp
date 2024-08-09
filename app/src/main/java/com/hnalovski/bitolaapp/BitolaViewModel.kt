package com.hnalovski.bitolaapp

import androidx.lifecycle.ViewModel
import com.hnalovski.bitolaapp.model.Category
import com.hnalovski.bitolaapp.model.Recommendation
import com.hnalovski.bitolaapp.model.RecommendationImage
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class BitolaViewModel : ViewModel() {
    private val _categories = MutableStateFlow<List<Category>>(emptyList())
    val categories: StateFlow<List<Category>> = _categories.asStateFlow()

    private val _recommendations = MutableStateFlow<List<Recommendation>>(emptyList())
    val recommendations: StateFlow<List<Recommendation>> = _recommendations.asStateFlow()

    private val _selectedPlace = MutableStateFlow<Recommendation?>(null)
    val selectedPlace: StateFlow<Recommendation?> = _selectedPlace.asStateFlow()

    init {
        loadCategories()
    }

    private fun loadCategories() {
        _categories.value = listOf(
            Category(id = 1, name = "Coffee Shops", image = R.drawable.coffee_shop_image),
            Category(id = 2, name = "Restaurants", image = R.drawable.restaurant_category_image),
        )
    }

    fun loadRecommendations(categoryId: Int) {
        _recommendations.value = when (categoryId) {
            1 -> listOf(
                Recommendation(
                    id = 1,
                    name = R.string.gt_coffee_name,
                    contact = R.string.gt_coffee_contact,
                    meals = R.string.gt_coffee_meals,
                    features = R.string.gt_coffee_features,
                    image = listOf(
                        RecommendationImage(image = R.drawable.gt_1),
                        RecommendationImage(image = R.drawable.gt_2),
                        RecommendationImage(image = R.drawable.gt_3),
                        RecommendationImage(image = R.drawable.gt_4),
                        RecommendationImage(image = R.drawable.gt_5),
                        RecommendationImage(image = R.drawable.gt_6),


                        ),
                    about = R.string.gt_coffee_about
                ),
                Recommendation(
                    id = 2,
                    name = R.string.jagoda_coffee_name,
                    contact = R.string.jagoda_coffee_contact,
                    meals = R.string.jagoda_coffee_meals,
                    features = R.string.jagoda_coffee_features,
                    image = listOf(
                        RecommendationImage(image = R.drawable.jagoda_1),
                        RecommendationImage(image = R.drawable.jagoda_2),
                        RecommendationImage(image = R.drawable.jagoda_3)
                    ),
                    about = R.string.jagoda_coffee_about
                )
            )

            2 -> listOf(
                Recommendation(
                    id = 3,
                    name = R.string.bourbon_restaurant_name,
                    contact = R.string.bourbon_restaurant_contact,
                    meals = R.string.bourbon_restaurant_meals,
                    features = R.string.bourbon_restaurant_features,
                    image = listOf(
                        RecommendationImage(image = R.drawable.bourbon_1),
                        RecommendationImage(image = R.drawable.bourbon_2),
                        RecommendationImage(image = R.drawable.bourbon_3),
                        RecommendationImage(image = R.drawable.bourbon_4),
                        RecommendationImage(image = R.drawable.bourbon_5),
                        RecommendationImage(image = R.drawable.bourbon_6),
                    ),
                    about = R.string.bourbon_restaurant_about
                ),
                Recommendation(
                    id = 4,
                    name = R.string.kuskus_restaurant_name,
                    contact = R.string.kuskus_restaurant_contact,
                    meals = R.string.kuskus_restaurant_meals,
                    features = R.string.kuskus_restaurant_features,
                    image = listOf(
                        RecommendationImage(image = R.drawable.gt_1),
                        RecommendationImage(image = R.drawable.gt_2),
                        RecommendationImage(image = R.drawable.gt_3),
                    ),
                    about = R.string.kuskus_restaurant_about
                ),
                Recommendation(
                    id = 5,
                    name = R.string.manaki_restaurant_name,
                    contact = R.string.manaki_restaurant_contact,
                    meals = R.string.manaki_restaurant_meals,
                    features = R.string.manaki_restaurant_features,
                    image = listOf(
                        RecommendationImage(image = R.drawable.manaki_1),
                        RecommendationImage(image = R.drawable.manaki_2),
                        RecommendationImage(image = R.drawable.manaki_3),
                        RecommendationImage(image = R.drawable.manaki_4),
                        RecommendationImage(image = R.drawable.manaki_5),
                    ),
                    about = R.string.manaki_restaurant_about
                ),
                Recommendation(
                    id = 6,
                    name = R.string.bure_restaurant_name,
                    contact = R.string.bure_restaurant_contact,
                    meals = R.string.bure_restaurant_meals,
                    features = R.string.bure_restaurant_features,
                    image = listOf(
                        RecommendationImage(image = R.drawable.bure_1),
                        RecommendationImage(image = R.drawable.bure_2),
                        RecommendationImage(image = R.drawable.bure_3),
                        RecommendationImage(image = R.drawable.bure_4),
                        RecommendationImage(image = R.drawable.bure_5),
                        RecommendationImage(image = R.drawable.bure_6),
                        RecommendationImage(image = R.drawable.bure_7),
                        RecommendationImage(image = R.drawable.bure_8),
                    ),
                    about = R.string.bure_restaurant_about
                ),
                Recommendation(
                    id = 7,
                    name = R.string.belvedere_restaurant_name,
                    contact = R.string.belverede_restaurant_contact,
                    meals = R.string.belverede_restaurant_meals,
                    features = R.string.belverede_restaurant_features,
                    image = listOf(
                        RecommendationImage(image = R.drawable.belvedere_1),
                        RecommendationImage(image = R.drawable.belvedere_2),
                        RecommendationImage(image = R.drawable.belvedere_3),
                        RecommendationImage(image = R.drawable.belvedere_4),
                        RecommendationImage(image = R.drawable.belvedere_5),
                    ),
                    about = R.string.belvedere_restaurant_about
                )
            )

            else -> emptyList()
        }
    }

    fun selectDetail(detailId: Int) {
        _selectedPlace.value = _recommendations.value.find { it.id == detailId }
    }
}