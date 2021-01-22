package com.example.movielist.views

import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import com.example.movielist.R
import com.example.movielist.helpers.DateTools
import com.example.movielist.models.MovieItem
import com.squareup.picasso.Picasso
import org.w3c.dom.Text

class MovieItemViewHolder(inflater: LayoutInflater, parent: ViewGroup) :
    RecyclerView.ViewHolder(inflater.inflate(R.layout.item_movie, parent, false)),
    View.OnClickListener {

    private var title: TextView? = null
    private var icon: ImageView? = null
    private var desc: TextView? = null

    private var start_date: TextView? = null
    private var end_date: TextView? = null

    private var type : TextView? = null
    private var rated: TextView? = null
    private var score: TextView? = null

    private var url: String? = null

    init {

        title = itemView.findViewById(R.id.tv_title)
        icon = itemView.findViewById(R.id.iv_icon)
        desc = itemView.findViewById(R.id.tv_desc)
        start_date = itemView.findViewById(R.id.tv_startdate)
        end_date = itemView.findViewById(R.id.tv_enddate)
        type = itemView.findViewById(R.id.tv_type)
        rated = itemView.findViewById(R.id.tv_rated)
        score = itemView.findViewById(R.id.tv_score)

        itemView.setOnClickListener(this)
    }

    fun bind(item:MovieItem)
    {
        title?.text = item.title
        desc?.text = item.synopsis
        start_date?.text = "Start: " + DateTools.convertTime(item.start_date)
        end_date?.text = "End: " + DateTools.convertTime(item.end_date)
        type?.text = item.type
        rated?.text = "Rated:" + item.rated
        score?.text = "Score:" + item.score.toString()

        url = item.url
        Picasso.get().load(item.image_url).into(icon)
    }

    override fun onClick(p0: View?) {

        val intent = Intent(
            Intent.ACTION_VIEW,
            Uri.parse(url)
        )
        p0?.let { startActivity(it?.context,intent,null) }
    }


}