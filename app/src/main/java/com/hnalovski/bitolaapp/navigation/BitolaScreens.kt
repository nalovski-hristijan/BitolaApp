package com.hnalovski.bitolaapp.navigation

enum class BitolaScreens(val route: String) {
    CATEGORY("categories"),
    RECOMMENDATION("recommendations/{categoryId}"),
    DETAIL("detail/{detailId}");

    companion object {
        fun recommendationsRoute(categoryId: Int) = "recommendations/$categoryId"
        fun detailsRoute(detailId: Int) = "detail/$detailId"
    }
}

