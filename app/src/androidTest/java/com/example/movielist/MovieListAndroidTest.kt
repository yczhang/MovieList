package com.example.movielist

import android.os.Build
import androidx.room.Room
import androidx.test.platform.app.InstrumentationRegistry
import com.example.movielist.database.MovieRoomDatabase
import com.example.movielist.models.MovieItem
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import org.junit.After

import org.junit.Test
import org.junit.runner.RunWith
import org.junit.Before
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config

@RunWith(RobolectricTestRunner::class)
class MovieListAndroidTest {
    private lateinit var database: MovieRoomDatabase
    private lateinit var item: MovieItem
    private var viewModelJob = Job()
    private val coroutineScope = CoroutineScope(viewModelJob + Dispatchers.Default)

    @Before
    fun initDb()
    {
        database = Room.inMemoryDatabaseBuilder(
            InstrumentationRegistry.getInstrumentation().getContext(),
            MovieRoomDatabase::class.java
        ).build()

        item = MovieItem(
            0,
            20,
        "https://myanimelist.net/anime/20/Naruto",
        "https://cdn.myanimelist.net/images/anime/13/17405.jpg?s=59241469eb470604a792add6fbe7cce6",
        "Naruto",
        false,
        "Moments prior to Naruto Uzumaki's birth, a huge demon known as the Kyuubi, the Nine-Tailed Fox, attacked Konohagakure, the Hidden Leaf Village, and wreaked havoc. In order to put an end to the Kyuubi'...",
        "TV",
        220,
                7.91.toFloat(),
        "2002-10-03T00:00:00+00:00",
        "2007-02-08T00:00:00+00:00",
        1739639,
        "PG-13"
        )
    }

    @After
    fun closeDb() {
        database.close()
    }
    @Test
    fun databaseTest() {
        coroutineScope.launch {
            database.movieDao().insert(item)

            val items = database.movieDao().getMovieList()

            assert(items.isEmpty())

            database.movieDao().deleteAll()

            val items1 = database.movieDao().getMovieList()

            assert(items1.isEmpty() == false)
        }
    }
}