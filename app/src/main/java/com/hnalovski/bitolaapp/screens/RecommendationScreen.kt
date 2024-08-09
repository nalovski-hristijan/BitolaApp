package com.hnalovski.bitolaapp.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.hnalovski.bitolaapp.BitolaViewModel
import com.hnalovski.bitolaapp.components.BitolaTopAppBar
import com.hnalovski.bitolaapp.model.Recommendation
import com.hnalovski.bitolaapp.navigation.BitolaScreens
import com.hnalovski.bitolaapp.utils.WindowStateUtils

@Composable
fun RecommendationScreen(
    viewModel: BitolaViewModel,
    navController: NavController,
    categoryId: Int,
    windowSize: WindowWidthSizeClass
) {
    val recommendations by viewModel.recommendations.collectAsState()
    val selectedPlace by viewModel.selectedPlace.collectAsState()

    // Load recommendations based on the categoryId
    LaunchedEffect(categoryId) {
        viewModel.loadRecommendations(categoryId)
    }

    val contentType: WindowStateUtils = when (windowSize) {
        WindowWidthSizeClass.Compact -> {
            WindowStateUtils.LIST_ONLY
        }

        WindowWidthSizeClass.Medium -> {
            WindowStateUtils.LIST_ONLY
        }

        WindowWidthSizeClass.Expanded -> {
            WindowStateUtils.LIST_AND_DETAIL
        }

        else -> WindowStateUtils.LIST_ONLY
    }

    Scaffold(
        topBar = {
            BitolaTopAppBar(isMainScreen = false, title = "Recommendations") {
                navController.popBackStack()
            }
        }
    ) { padding ->
        if (contentType == WindowStateUtils.LIST_AND_DETAIL) {
            Row(Modifier.padding(padding)) {
                LazyColumn(modifier = Modifier.weight(1f)) {
                    items(recommendations) { recommendation ->
                        RecommendationItem(recommendation = recommendation) {
                            viewModel.selectDetail(recommendation.id)
                        }
                    }
                }
                val placeToSHow = selectedPlace ?: recommendations.firstOrNull()

                if (placeToSHow != null) {
                    DetailContent(recommendation = placeToSHow, modifier = Modifier.weight(2f))
                }


            }
        } else {
            LazyColumn(modifier = Modifier.padding(padding)) {
                items(recommendations) { recommendation ->
                    RecommendationItem(recommendation = recommendation) {
                        navController.navigate(BitolaScreens.detailsRoute(recommendation.id))
                    }
                }
            }
        }
    }
}


@Composable
fun DetailContent(recommendation: Recommendation, modifier: Modifier) {
    Column(
        modifier = modifier
            .padding(16.dp)
            .verticalScroll(rememberScrollState())
    ) {
        LazyRow {
            items(items = recommendation.image) { image ->
                DetailsImageRow(item = image)
            }
        }

        Text(
            modifier = Modifier.padding(top = 8.dp),
            text = stringResource(id = recommendation.about),
            style = MaterialTheme.typography.titleMedium
        )

        Text(
            modifier = Modifier.padding(top = 8.dp),
            text = stringResource(id = recommendation.features),
            style = MaterialTheme.typography.titleMedium
        )

        Text(
            modifier = Modifier.padding(top = 8.dp),
            text = stringResource(id = recommendation.meals),
            style = MaterialTheme.typography.titleMedium
        )

        Text(
            modifier = Modifier.padding(top = 8.dp),
            text = stringResource(id = recommendation.contact),
            style = MaterialTheme.typography.titleMedium
        )
    }
}


@Composable
fun RecommendationItem(recommendation: Recommendation, onClick: () -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .clickable(onClick = onClick),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Row {
            Image(
                modifier = Modifier.size(120.dp),
                painter = painterResource(id = recommendation.image.first().image),
                contentDescription = null,
                contentScale = ContentScale.Crop
            )
            Column(modifier = Modifier.padding(16.dp)) {
                Text(text = stringResource(id = recommendation.name), fontWeight = FontWeight.Bold)

                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = stringResource(id = recommendation.about),
                    style = MaterialTheme.typography.bodyMedium,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis
                )
            }

        }
    }
}


