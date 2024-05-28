package com.alcorp.moviecatalogue.ui.detail

import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.alcorp.moviecatalogue.R
import com.alcorp.moviecatalogue.data.MovieEntity
import com.alcorp.moviecatalogue.data.TvShowEntity
import com.alcorp.moviecatalogue.utils.viewmodel.ViewModelFactory
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import kotlinx.android.synthetic.main.activity_detail.*

class DetailActivity : AppCompatActivity(), SwipeRefreshLayout.OnRefreshListener {

    companion object {
        const val EXTRA_ID = "extra_id"
        const val EXTRA_TITLE = "extra_title"
        const val EXTRA_TYPE = "extra_type"
    }

    private lateinit var viewModel: DetailViewModel

    private lateinit var id: String
    private lateinit var title: String
    private lateinit var type: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        setToolbar()

        val factory = ViewModelFactory.getInstance()
        viewModel = ViewModelProvider(this, factory)[DetailViewModel::class.java]

        val extras = intent.extras
        if (extras != null){
            id = extras.getString(EXTRA_ID).toString()
            title = extras.getString(EXTRA_TITLE).toString()
            type = extras.getString(EXTRA_TYPE).toString()

            viewModel.setSelected(title)

            progBar_detail.visibility = View.VISIBLE
            if (checkNetwork(this)){
                progBar_detail.visibility = View.GONE
                if (type == resources.getString(R.string.movie)){
                    viewModel.getDetailMovie(id).observe(this, Observer { detail ->
                        setMovie(detail)
                    })
                } else {
                    viewModel.getDetailTv(id).observe(this, Observer { detail ->
                        setTvShow(detail)
                    })
                }
            } else {
                progBar_detail.visibility = View.GONE
                Toast.makeText(this@DetailActivity, resources.getString(R.string.txt_toast_network), Toast.LENGTH_SHORT).show()
            }
        }
        refresh_detail.setOnRefreshListener(this)
    }

    override fun onRefresh() {
        progBar_detail.visibility = View.VISIBLE
        if (checkNetwork(this)){
            progBar_detail.visibility = View.GONE
            if (type == resources.getString(R.string.movie)){
                viewModel.getDetailMovie(id).observe(this, Observer { detail ->
                    setMovie(detail)
                })
            } else {
                viewModel.getDetailTv(id).observe(this, Observer { detail ->
                    setTvShow(detail)
                })
            }
        } else {
            progBar_detail.visibility = View.GONE
            Toast.makeText(this@DetailActivity, resources.getString(R.string.txt_toast_network), Toast.LENGTH_SHORT).show()
        }
        refresh_detail.isRefreshing = false
    }

    private fun setToolbar(){
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

    private fun checkNetwork(context: Context?): Boolean{
        val cm: ConnectivityManager = context?.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val network =
            if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
                cm.getNetworkCapabilities(cm.activeNetwork)
            } else {
                cm.activeNetworkInfo
            }
        return network != null
    }

    private fun setMovie(movieEntity: MovieEntity) {
        tvTitle.text = movieEntity.title
        tvYear.text = movieEntity.yearRelease
        tvOview.text = movieEntity.synopsis

        Glide.with(this)
                .load("https://image.tmdb.org/t/p/original"+movieEntity.imagePath)
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
                .load("https://image.tmdb.org/t/p/original"+tvShowEntity.imagePath)
                .apply(RequestOptions.placeholderOf(R.drawable.ic_loading)
                        .override(250, 350)
                        .error(R.drawable.ic_error))
                .into(img_poster)
    }
}