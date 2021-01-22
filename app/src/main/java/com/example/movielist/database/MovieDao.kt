package com.example.movielist.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.movielist.models.MovieItem

@Dao
interface MovieDao {

    @Query("select * from movie_table")
    fun getMovieList(): List<MovieItem>

    @Insert
    fun insert(item: MovieItem)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(items: List<MovieItem>)

    @Query("DELETE FROM movie_table")
    fun deleteAll()
}