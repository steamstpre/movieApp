package com.example.movieapp

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.movieapp.data.models.ShowItem
import com.example.movieapp.data.networking.ApiRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val repository: ApiRepository) : ViewModel() {

    private val _allMovieLiveData = MutableLiveData<List<ShowItem>>()
    val allMovies: LiveData<List<ShowItem>>
        get() = _allMovieLiveData

    fun  getAllMovies() {
        viewModelScope.launch {
            try {
                val response = repository.getAllMovies()
                if (response.isSuccessful) {
                    _allMovieLiveData.postValue(response.body())
                } else {
                    Log.e("check data", "Failed: ${response.errorBody()?.string()}")
                }
            } catch (e: Exception) {
                Log.e("check data", "Exception: ${e.message}")
            }
        }

    }
}