package com.alcorp.moviecatalogue.ui.movie

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.alcorp.moviecatalogue.R
import com.alcorp.moviecatalogue.data.MovieEntity
import com.alcorp.moviecatalogue.ui.detail.DetailActivity
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import kotlinx.android.synthetic.main.item_list.view.*

class MovieAdapter : RecyclerView.Adapter<MovieAdapter.MovieViewHolder>() {
    private var listMovie = ArrayList<MovieEntity>()

    fun setMovie(movies: List<MovieEntity>){
        if (movies == null) return
        listMovie.clear()
        listMovie.addAll(movies)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_list, parent, false)
        return MovieViewHolder(view)
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        val movie = listMovie[position]
        holder.bind(movie)
    }

    override fun getItemCount(): Int = listMovie.size

    class MovieViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(movie: MovieEntity){
            with(itemView){
                tvItemTitle.text = movie.title
                tvItemYear.text = movie.yearRelease

                Glide.with(context)
                        .load(resources.getString(R.string.txt_image_url) + movie.imagePath)
                        .apply(RequestOptions.placeholderOf(R.drawable.ic_loading)
                                .override(120, 150)
                                .error(R.drawable.ic_error))
                        .into(img_poster)

                setOnClickListener {
                    val intent = Intent(context, DetailActivity::class.java)
                    intent.putExtra(DetailActivity.EXTRA_ID, movie.movieId)
                    intent.putExtra(DetailActivity.EXTRA_TITLE, movie.title)
                    intent.putExtra(DetailActivity.EXTRA_TYPE, resources.getString(R.string.movie))
                    itemView.context.startActivity(intent)
                }
            }
        }
    }
}