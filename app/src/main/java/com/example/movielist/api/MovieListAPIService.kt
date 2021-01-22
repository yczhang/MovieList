package com.example.movielist.api

import com.example.movielist.models.MovieListReponse
import com.example.movielist.network.getRetrofit
import kotlinx.coroutines.Deferred
import retrofit2.Response
import retrofit2.http.*

interface MovieListAPIService {
    @GET("/v3/search/anime")
    fun fetch(@Query("q") keyword:String): Deferred<Response<MovieListReponse>>
}

object MovieListAPI {
    val retrofitService: MovieListAPIService by lazy{
        getRetrofit().create(MovieListAPIService::class.java)
    }
}