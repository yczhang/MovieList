package com.example.movielist.viewmodels

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.movielist.api.MovieListAPI
import com.example.movielist.database.MovieRepository
import com.example.movielist.database.MovieRoomDatabase
import com.example.movielist.models.MovieItem
import com.example.movielist.models.MovieListReponse
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class MainViewModel(application: Application) : AndroidViewModel(application) {

    private val _isReady = MutableLiveData<Boolean>(false)

    private var movieResponse: MovieListReponse? = null

    private var items: List<MovieItem>? = null

    val isReady: LiveData<Boolean>
        get() = _isReady

    private var viewModelJob = Job()

    private val coroutineScope = CoroutineScope(viewModelJob + Dispatchers.Main)
    private val coroutineDatabaseScope = CoroutineScope(viewModelJob + Dispatchers.IO)

    private val repository: MovieRepository

    init {
        val movieDao = MovieRoomDatabase.getDatabase(application.applicationContext, coroutineDatabaseScope).movieDao()

        repository = MovieRepository(movieDao)

    }

    public fun loadMovieList(keyword:String)
    {
        coroutineScope.launch {

                val request = MovieListAPI.retrofitService.fetch(keyword)
                try {
                    val result = request.await()

                    if (result.isSuccessful) {
                        movieResponse = result.body()

                        coroutineDatabaseScope.launch {

                            repository.dellAllMovies()

                            movieResponse?.results?.let {
                                repository.insertAll(it)
                            }

                            items = repository.getAllMovies()
                        }
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
        return items ?: listOf()
    }
}