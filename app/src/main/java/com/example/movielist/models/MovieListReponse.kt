package com.example.movielist.models

import com.squareup.moshi.Json
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

data class MovieListReponse (
    @Json(name="results")
    val results : List<MovieItem>? = null
        )

@Entity(tableName = "movie_table")
data class MovieItem(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "table_id")
    val table_id: Int = 0,

    @ColumnInfo(name = "mal_id")
    @Json(name = "mal_id")
    val mal_id : Int?,

    @ColumnInfo(name = "url")
    @Json(name = "url")
    val url: String?,

    @ColumnInfo(name = "image_url")
    @Json(name = "image_url")
    val image_url: String?,

    @ColumnInfo(name = "title")
    @Json(name = "title")
    val title: String?,

    @ColumnInfo(name = "airing")
    @Json(name = "airing")
    val airing: Boolean?,

    @ColumnInfo(name = "synopsis")
    @Json(name = "synopsis")
    val synopsis: String?,

    @ColumnInfo(name = "type")
    @Json(name = "type")
    val type : String?,

    @ColumnInfo(name = "episodes")
    @Json(name = "episodes")
    val episodes:Int?,

    @ColumnInfo(name = "score")
    @Json(name = "score")
    val score:Float?,

    @ColumnInfo(name = "start_date")
    @Json(name = "start_date")
    val start_date:String?,

    @ColumnInfo(name = "end_date")
    @Json(name = "end_date")
    val end_date:String?,
    @ColumnInfo(name = "members")
    @Json(name = "members")
    val members:Int?,

    @ColumnInfo(name = "rated")
    @Json(name = "rated")
    val rated:String?
)


