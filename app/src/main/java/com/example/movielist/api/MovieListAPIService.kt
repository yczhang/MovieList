package com.example.movielist.api

import com.example.movielist.models.MovieListReponse
import com.example.movielist.network.getRetrofit
import kotlinx.coroutines.Deferred
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST

interface MovieListAPIService {
    @GET("/v3/search/anime?q=naruto")
    fun fetch(): Deferred<Response<MovieListReponse>>
}

object MovieListAPI {
    val retrofitService: MovieListAPIService by lazy{
        getRetrofit().create(MovieListAPIService::class.java)
    }
}