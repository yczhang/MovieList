package com.example.movielist.models

import com.squareup.moshi.Json

data class MovieListReponse (
    @Json(name="results")
    val results : List<MovieItem>? = null
        )


data class MovieItem(

    @Json(name = "mal_id")
    val mal_id : Int?,
    @Json(name = "url")
    val url: String?,
    @Json(name = "image_url")
    val image_url: String?,
    @Json(name = "title")
    val title: String?,
    @Json(name = "airing")
    val airing: Boolean?,
    @Json(name = "synopsis")
    val synopsis: String?,
    @Json(name = "type")
    val type : String?,
    @Json(name = "episodes")
    val episodes:Int?,
    @Json(name = "score")
    val score:Float?,
    @Json(name = "start_date")
    val start_date:String?,
    @Json(name = "end_date")
    val end_date:String?,
    @Json(name = "members")
    val members:Int?,
    @Json(name = "rated")
    val rated:String?
)


