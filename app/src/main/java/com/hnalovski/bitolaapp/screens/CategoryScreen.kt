package com.hnalovski.bitolaapp.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.hnalovski.bitolaapp.BitolaViewModel
import com.hnalovski.bitolaapp.R
import com.hnalovski.bitolaapp.components.BitolaTopAppBar
import com.hnalovski.bitolaapp.model.Category
import com.hnalovski.bitolaapp.navigation.BitolaScreens

@Composable
fun CategoryScreen(viewModel: BitolaViewModel, navController: NavController) {
    val categories by viewModel.categories.collectAsState()

    Scaffold(
        topBar = { BitolaTopAppBar(isMainScreen = true, title = "Bitola City Guide") }
    ) { padding ->
        LazyColumn(modifier = Modifier.padding(padding)) {
            items(categories) { category ->
                CategoryItem(category = category) {
                    navController.navigate(BitolaScreens.recommendationsRoute(category.id))
                }
            }
        }
    }
}

@Preview
@Composable
fun CategoryItem(
    category: Category = Category(1, "Restaurants", R.drawable.restaurant_category_image),
    onClick: () -> Unit = {}
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .clickable(onClick = onClick),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Start
        ) {
            Image(
                modifier = Modifier.size(120.dp),
                painter = painterResource(id = category.image),
                contentDescription = "category image",
                contentScale = ContentScale.Crop
            )
            Text(
                modifier = Modifier.padding(start = 20.dp),
                text = category.name,
                style = MaterialTheme.typography.headlineMedium
            )
        }
    }
}