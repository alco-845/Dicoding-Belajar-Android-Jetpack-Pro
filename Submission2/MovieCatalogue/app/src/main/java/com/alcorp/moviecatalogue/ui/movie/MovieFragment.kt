package com.alcorp.moviecatalogue.ui.movie

import android.content.Context
import android.net.ConnectivityManager
import android.os.Build
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.alcorp.moviecatalogue.R
import com.alcorp.moviecatalogue.utils.viewmodel.ViewModelFactory
import kotlinx.android.synthetic.main.fragment_movie.*

class MovieFragment : Fragment(), SwipeRefreshLayout.OnRefreshListener {

    private lateinit var movieAdapter: MovieAdapter
    private lateinit var viewModel: MovieViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_movie, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        if (activity != null){
            movieAdapter = MovieAdapter()

            val factory = ViewModelFactory.getInstance()
            viewModel = ViewModelProvider(this, factory)[MovieViewModel::class.java]

            progBar_movie.visibility = View.VISIBLE
            if (checkNetwork(context)){
                viewModel.getListMovie().observe(viewLifecycleOwner, Observer { results ->
                    progBar_movie.visibility = View.GONE
                    movieAdapter.setMovie(results)
                    movieAdapter.notifyDataSetChanged()

                    with(rec_movie) {
                        layoutManager = GridLayoutManager(context, 2, GridLayoutManager.VERTICAL, false)
                        setHasFixedSize(true)
                        adapter = movieAdapter
                    }
                })
            } else {
                progBar_movie.visibility = View.GONE
                Toast.makeText(context, resources.getString(R.string.txt_toast_network), Toast.LENGTH_SHORT).show()
            }
        }

        refresh_movie.setOnRefreshListener(this)
    }

    override fun onRefresh() {
        progBar_movie.visibility = View.VISIBLE
        if (checkNetwork(context)){
            progBar_movie.visibility = View.GONE
            viewModel.getListMovie().observe(viewLifecycleOwner, Observer { results ->
                progBar_movie.visibility = View.GONE
                movieAdapter.setMovie(results)
                movieAdapter.notifyDataSetChanged()

                with(rec_movie) {
                    layoutManager = GridLayoutManager(context, 2, GridLayoutManager.VERTICAL, false)
                    setHasFixedSize(true)
                    adapter = movieAdapter
                }
            })
        } else {
            progBar_movie.visibility = View.GONE
            Toast.makeText(context, resources.getString(R.string.txt_toast_network), Toast.LENGTH_SHORT).show()
        }
        refresh_movie.isRefreshing = false
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
}