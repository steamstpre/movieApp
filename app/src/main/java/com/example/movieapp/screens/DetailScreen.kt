package com.example.movieapp.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.systemBars
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.annotation.ExperimentalCoilApi
import coil.compose.rememberImagePainter
import com.example.movieapp.MainViewModel
import com.example.movieapp.data.models.ShowItem
import com.example.movieapp.utils.HtmlText

@OptIn(ExperimentalCoilApi::class)
@Composable
fun DetailScreen(viewModel: MainViewModel, itemId: String) {
    val currentItem = viewModel.allMovies
        .observeAsState(listOf()).value
        .firstOrNull {
            it.id == itemId.toInt()
        }


    Surface(
        modifier = Modifier
            .fillMaxSize()
            .padding(WindowInsets.systemBars.asPaddingValues())
    ) {
       LazyColumn {
           item {
               Column (
                   horizontalAlignment = Alignment.CenterHorizontally
               ) {
                   Image(
                       painter = rememberImagePainter(currentItem?.image?.medium),
                       contentDescription = null,
                       modifier = Modifier.size(512.dp)
                   )
                   Text(
                       text = currentItem?.name ?: "",
                       fontWeight = FontWeight.Bold,
                       fontSize = 32.sp,
                       modifier = Modifier.padding(top = 16.dp)
                   )
                   Row (
                       modifier = Modifier
                           .padding(top = 8.dp)
                   ) {
                       Text(
                           text = "Rating ",
                           fontWeight = FontWeight.Bold
                       )
                       Text(currentItem?.rating.toString())
                   }
                   Row (
                       modifier = Modifier
                           .padding(top = 8.dp)
                   ) {
                       Text(
                           text = "Genre ",
                           fontWeight = FontWeight.Bold
                       )
                       currentItem?.genres?.take(2)?.forEach {
                           Text("$it ")
                       }
                   }
                   Row (
                       modifier = Modifier
                           .padding(top = 8.dp)
                   ){
                       Text(
                           text = "Premier ",
                           fontWeight = FontWeight.Bold
                       )
                       Text(currentItem?.premiered ?: "")
                   }
                   HtmlText(
                       currentItem?.summary ?: "",
                       modifier = Modifier
                           .padding(horizontal = 16.dp)
                   )

               }
           }
       }
    }
}