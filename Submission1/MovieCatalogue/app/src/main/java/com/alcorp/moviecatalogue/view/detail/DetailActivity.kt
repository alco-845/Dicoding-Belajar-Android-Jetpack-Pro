package com.alcorp.moviecatalogue.view.detail

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.lifecycle.ViewModelProvider
import com.alcorp.moviecatalogue.R
import com.alcorp.moviecatalogue.data.MovieEntity
import com.alcorp.moviecatalogue.data.TvShowEntity
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import kotlinx.android.synthetic.main.activity_detail.*

class DetailActivity : AppCompatActivity() {

    companion object {
        const val EXTRA_TITLE = "extra_title"
        const val EXTRA_TYPE = "extra_type"
    }

    private lateinit var title: String
    private lateinit var type: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        setToolbar()

        val viewModel = ViewModelProvider(this, ViewModelProvider.NewInstanceFactory())[DetailViewModel::class.java]

        val extras = intent.extras
        if (extras != null){
            title = extras.getString(EXTRA_TITLE).toString()
            if (title != null){
                viewModel.setTitle(title)
                type = extras.getString(EXTRA_TYPE).toString()
                if (type == "Movie"){
                    setMovie(viewModel.getMovie())
                } else {
                    setTvShow(viewModel.getTvShow())
                }
            }
        }
    }

    fun setToolbar(){
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = resources.getString(R.string.txt_detail)
        supportActionBar?.elevation = 0F
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_toolbar, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home){
            finish()
        } else {
            val share = Intent(Intent.ACTION_SEND)
            share.type = "text/plain"
            share.putExtra(Intent.EXTRA_TEXT, "Hey, I recommended you a $type named $title")
            startActivity(Intent.createChooser(share, resources.getString(R.string.txt_share)))
        }

        return super.onOptionsItemSelected(item)
    }

    private fun setMovie(movieEntity: MovieEntity) {
        tvTitle.text = movieEntity.title
        tvYear.text = movieEntity.yearRelease
        tvOview.text = movieEntity.synopsis

        Glide.with(this)
                .load(movieEntity.imagePath)
                .apply(RequestOptions.placeholderOf(R.drawable.ic_loading)
                        .override(250, 350)
                        .error(R.drawable.ic_error))
                .into(img_poster)
    }

    private fun setTvShow(tvShowEntity: TvShowEntity) {
        tvTitle.text = tvShowEntity.title
        tvYear.text = tvShowEntity.yearRelease
        tvOview.text = tvShowEntity.synopsis

        Glide.with(this)
                .load(tvShowEntity.imagePath)
                .apply(RequestOptions.placeholderOf(R.drawable.ic_loading)
                        .override(250, 350)
                        .error(R.drawable.ic_error))
                .into(img_poster)
    }
}