package com.example.movieapp.data.networking
import com.example.movieapp.data.models.Show
import com.example.movieapp.data.models.ShowItem
import retrofit2.http.GET
import retrofit2.Response

interface ApiService {
    @GET("/shows")
    suspend fun getAllMovies() : Response<List<ShowItem>>
}