package com.example.movieapp.screens

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.systemBars
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.annotation.ExperimentalCoilApi
import coil.compose.rememberImagePainter
import com.example.movieapp.MainViewModel
import com.example.movieapp.data.models.ShowItem
import com.example.movieapp.navigation.Screens
import com.example.movieapp.utils.Constants

@Composable
fun MainScreen(navController: NavController, viewModel: MainViewModel) {
    val allMovies = viewModel.allMovies.observeAsState(listOf()).value
    allMovies.forEach {
        Log.d("check data", it.name)
    }

    Surface(
        modifier = Modifier
            .fillMaxSize()
            .padding(WindowInsets.systemBars.asPaddingValues())
    ) {
       LazyColumn (
           modifier = Modifier
               .padding(20.dp)
       ) {
           items(allMovies.take(10)) { item ->  
               MovieItem(item = item, navController = navController)
           }
       }
    }
}

@OptIn(ExperimentalCoilApi::class)
@Composable
fun MovieItem(item: ShowItem, navController: NavController) {
    Card (
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
        modifier = Modifier
            .padding(top = 8.dp)
            .clickable {
                navController.navigate("${Screens.Detail.route}/${item.id}")
            }
    ) {
        Row (
            modifier = Modifier
                .fillMaxSize()
                .padding(vertical = 16.dp)
        ) {
            Image(
                painter = rememberImagePainter(item.image.medium),
                contentDescription = null,
                Modifier.size(120.dp),
            )
            Column {
                Text(
                    text = item.name,
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold
                )
                Row {
                    Text(
                        text = "Rating ",
                        fontWeight = FontWeight.Bold
                    )
                    Text(item.rating.toString())
                }
                Row {
                    Text(
                        text = "Genre ",
                        fontWeight = FontWeight.Bold
                    )
                    item.genres.take(2).forEach {
                        Text(it)
                    }
                }
                Row {
                    Text(
                        text = "Premier ",
                        fontWeight = FontWeight.Bold
                    )
                    Text(item.premiered)
                }
            }
        }
    }

}