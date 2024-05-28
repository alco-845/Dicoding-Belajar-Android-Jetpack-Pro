package com.alcorp.moviecatalogue.ui.tvShow

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.alcorp.moviecatalogue.R
import com.alcorp.moviecatalogue.data.TvShowEntity
import com.alcorp.moviecatalogue.ui.detail.DetailActivity
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import kotlinx.android.synthetic.main.item_list.view.*

class TvShowAdapter : RecyclerView.Adapter<TvShowAdapter.TvShowViewHolder>() {
    private var listTvShow = ArrayList<TvShowEntity>()

    fun setTvShow(tvShows: List<TvShowEntity>?){
        if (tvShows == null) return
        listTvShow.clear()
        listTvShow.addAll(tvShows)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TvShowViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_list, parent, false)
        return TvShowViewHolder(view)
    }

    override fun onBindViewHolder(holder: TvShowViewHolder, position: Int) {
        val tvShow = listTvShow[position]
        holder.bind(tvShow)
    }

    override fun getItemCount(): Int = listTvShow.size

    class TvShowViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(tvShow: TvShowEntity){
            with(itemView){
                tvItemTitle.text = tvShow.title
                tvItemYear.text = tvShow.yearRelease

                Glide.with(context)
                        .load(resources.getString(R.string.txt_image_url) + tvShow.imagePath)
                        .apply(RequestOptions.placeholderOf(R.drawable.ic_loading)
                                .override(120, 150)
                                .error(R.drawable.ic_error))
                        .into(img_poster)

                setOnClickListener {
                    val intent = Intent(context, DetailActivity::class.java)
                    intent.putExtra(DetailActivity.EXTRA_ID, tvShow.tvShowId)
                    intent.putExtra(DetailActivity.EXTRA_TITLE, tvShow.title)
                    intent.putExtra(DetailActivity.EXTRA_TYPE, resources.getString(R.string.tvshow))
                    itemView.context.startActivity(intent)
                }
            }
        }
    }
}