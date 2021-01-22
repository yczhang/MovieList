package com.example.movielist.viewmodels

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.movielist.R
import com.example.movielist.api.MovieListAPI
import com.example.movielist.database.MovieRepository
import com.example.movielist.database.MovieRoomDatabase
import com.example.movielist.models.MovieItem
import com.example.movielist.models.MovieListReponse
import com.google.gson.Gson
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class MainViewModel(application: Application) : AndroidViewModel(application) {

    private val _isReady = MutableLiveData<Boolean>()

    private var movieResponse: MovieListReponse? = null

    private var items: List<MovieItem>? = null

    val isReady: LiveData<Boolean>
        get() = _isReady

    private var viewModelJob = Job()

    private val coroutineScope = CoroutineScope(viewModelJob + Dispatchers.Main)
    private val coroutineDatabaseScope = CoroutineScope(viewModelJob + Dispatchers.IO)

    private var repository: MovieRepository? = null

    init {

        application.applicationContext?.let {
            val movieDao = MovieRoomDatabase.getDatabase(it, coroutineDatabaseScope).movieDao()
            repository = MovieRepository(movieDao)
        }
    }

    public fun loadLocalMovieList(resourceID:Int)
    {
        try {
            val inputStream = getApplication<Application>().resources.openRawResource(R.raw.sampledata)

            val jsonStr = inputStream.bufferedReader().use {
                it.readText()
            }

            movieResponse = Gson().fromJson<MovieListReponse>(jsonStr, MovieListReponse::class.java)

            coroutineDatabaseScope.launch {

                repository?.dellAllMovies()

                movieResponse?.results?.let {
                    repository?.insertAll(it)
                }

                items = repository?.getAllMovies()

                coroutineScope.launch {
                    _isReady.value = true
                }
            }
        } catch (e: Exception)
        {

        }
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

                            repository?.dellAllMovies()

                            movieResponse?.results?.let {
                                repository?.insertAll(it)
                            }

                            items = repository?.getAllMovies()

                            coroutineScope.launch{
                                _isReady.value = true
                            }
                        }


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

    fun getItemsCount() : Int
    {
        return items?.size ?: 0
    }

    fun getItem(index:Int): MovieItem?
    {
        return items?.get(index)
    }
}