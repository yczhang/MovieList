package com.example.movielist.database

import com.example.movielist.models.MovieItem

class MovieRepository (private val movieDao: MovieDao){
    fun insertAll(items: List<MovieItem>){
        movieDao.insertAll(items)
    }

    fun insert(item: MovieItem){
        movieDao.insert(item)
    }

    fun getAllOffer(): List<MovieItem> {
        return movieDao.getMovieList()
    }

    fun dellAllOffer()
    {
        movieDao.deleteAll()
    }
}