package com.example.movielist.viewmodels

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.movielist.api.MovieListAPI
import com.example.movielist.models.MovieItem
import com.example.movielist.models.MovieListReponse
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class MainViewModel : ViewModel() {

    private val _isReady = MutableLiveData<Boolean>(false)

    private var movieResponse: MovieListReponse? = null

    val isReady: LiveData<Boolean>
        get() = _isReady

    private var viewModelJob = Job()

    private val coroutineScope = CoroutineScope(viewModelJob + Dispatchers.Main)

    init {

    }

    public fun loadMovieList(keyword:String)
    {
        coroutineScope.launch {
            val request = MovieListAPI.retrofitService.fetch(keyword)

            try {
              val result = request.await()

                if( result.isSuccessful)
                {
                    movieResponse = result.body()

                    _isReady.value = true

                } else {
                    _isReady.value = false
                }
            } catch (e: Exception) {
            }
        }
    }

    fun getItems() : List<MovieItem>
    {
        movieResponse?.results?.let {
            return it
        }

        return listOf()
    }
}