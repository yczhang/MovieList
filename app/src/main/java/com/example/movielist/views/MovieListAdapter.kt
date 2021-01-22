package com.example.movielist.views

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.movielist.models.MovieItem

class MovieListAdapter(private val list: List<MovieItem>) : RecyclerView.Adapter<MovieItemViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieItemViewHolder {

        val inflater = LayoutInflater.from(parent.context)

        return MovieItemViewHolder(inflater,parent)

    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: MovieItemViewHolder, position: Int) {

        holder.bind(list[position])
    }
}