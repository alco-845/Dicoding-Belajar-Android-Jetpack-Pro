package com.alcorp.moviecatalogue.ui.tvShow

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
import kotlinx.android.synthetic.main.fragment_tv_show.*

class TvShowFragment : Fragment(), SwipeRefreshLayout.OnRefreshListener {

    private lateinit var tvAdapter: TvShowAdapter
    private lateinit var viewModel: TvShowViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_tv_show, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        if (activity != null){
            tvAdapter = TvShowAdapter()

            val factory = ViewModelFactory.getInstance()
            viewModel = ViewModelProvider(this, factory)[TvShowViewModel::class.java]

            progBar_tv.visibility = View.VISIBLE
            if (checkNetwork(context)){
                viewModel.getListTv().observe(viewLifecycleOwner, Observer { results ->
                    progBar_tv.visibility = View.GONE
                    tvAdapter.setTvShow(results)
                    tvAdapter.notifyDataSetChanged()

                    with(rec_tvshow) {
                        layoutManager = GridLayoutManager(context, 2, GridLayoutManager.VERTICAL, false)
                        setHasFixedSize(true)
                        adapter = tvAdapter
                    }
                })
            } else {
                progBar_tv.visibility = View.GONE
                Toast.makeText(context, resources.getString(R.string.txt_toast_network), Toast.LENGTH_SHORT).show()
            }
        }

        refresh_tv.setOnRefreshListener(this)
    }

    override fun onRefresh() {
        progBar_tv.visibility = View.VISIBLE
        if (checkNetwork(context)){
            progBar_tv.visibility = View.GONE
            viewModel.getListTv().observe(viewLifecycleOwner, Observer { results ->
                progBar_tv.visibility = View.GONE
                tvAdapter.setTvShow(results)
                tvAdapter.notifyDataSetChanged()

                with(rec_tvshow) {
                    layoutManager = GridLayoutManager(context, 2, GridLayoutManager.VERTICAL, false)
                    setHasFixedSize(true)
                    adapter = tvAdapter
                }
            })
        } else {
            progBar_tv.visibility = View.GONE
            Toast.makeText(context, resources.getString(R.string.txt_toast_network), Toast.LENGTH_SHORT).show()
        }
        refresh_tv.isRefreshing = false
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