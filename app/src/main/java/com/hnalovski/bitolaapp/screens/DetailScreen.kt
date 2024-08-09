package com.hnalovski.bitolaapp.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Card
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.hnalovski.bitolaapp.BitolaViewModel
import com.hnalovski.bitolaapp.R
import com.hnalovski.bitolaapp.components.BitolaTopAppBar
import com.hnalovski.bitolaapp.model.RecommendationImage

@Composable
fun DetailScreen(viewModel: BitolaViewModel, navController: NavController, detailId: Int) {
    val detail by viewModel.selectedPlace.collectAsState()

    viewModel.selectDetail(detailId)


    Scaffold(
        topBar = {
            BitolaTopAppBar(
                isMainScreen = false,
                title = stringResource(id = detail?.name ?: R.string.app_name)
            ) {
                navController.popBackStack()
            }
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .verticalScroll(rememberScrollState()),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.Start
        ) {
            LazyRow {
                detail?.let {
                    items(items = it.image) { items ->
                        DetailsImageRow(item = items)
                    }
                }
            }
            Text(
                modifier = Modifier.padding(start = 10.dp, bottom = 10.dp),
                text = "About",
                style = MaterialTheme.typography.headlineMedium
            )

            Text(
                modifier = Modifier.padding(start = 10.dp, bottom = 10.dp),
                text = stringResource(id = detail?.about ?: R.string.empty_text_placeholder),
                style = MaterialTheme.typography.titleMedium,
                textAlign = TextAlign.Start
            )

            Text(
                modifier = Modifier.padding(start = 10.dp, bottom = 10.dp),
                text = "Features",
                style = MaterialTheme.typography.headlineMedium
            )

            Text(
                modifier = Modifier.padding(start = 10.dp, bottom = 10.dp),
                text = stringResource(id = detail?.features ?: R.string.empty_text_placeholder),
                style = MaterialTheme.typography.titleMedium,
                textAlign = TextAlign.Start
            )

            Text(
                modifier = Modifier.padding(start = 10.dp, bottom = 10.dp),
                text = "Meals",
                style = MaterialTheme.typography.headlineMedium
            )

            Text(
                modifier = Modifier.padding(start = 10.dp, bottom = 10.dp),
                text = stringResource(id = detail?.meals ?: R.string.empty_text_placeholder),
                style = MaterialTheme.typography.titleMedium,
                textAlign = TextAlign.Start
            )

            Text(
                modifier = Modifier.padding(start = 10.dp, bottom = 10.dp),
                text = "Contact",
                style = MaterialTheme.typography.headlineMedium
            )

            Text(
                modifier = Modifier.padding(start = 10.dp, bottom = 10.dp),
                text = stringResource(id = detail?.contact ?: R.string.empty_text_placeholder),
                style = MaterialTheme.typography.titleMedium,
                textAlign = TextAlign.Start
            )


        }
    }
}


@Composable
fun DetailsImageRow(item: RecommendationImage) {
    Card(
        modifier = Modifier
            .padding(10.dp)
            .background(Color.White)
    ) {
        Image(
            modifier = Modifier.size(250.dp),
            painter = painterResource(id = item.image),
            contentDescription = "detail images",
            contentScale = ContentScale.Crop
        )
    }
}
