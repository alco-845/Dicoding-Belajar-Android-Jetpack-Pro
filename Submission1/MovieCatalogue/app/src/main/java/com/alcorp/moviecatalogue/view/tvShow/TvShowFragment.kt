package com.alcorp.moviecatalogue.view.tvShow

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.alcorp.moviecatalogue.R
import kotlinx.android.synthetic.main.fragment_tv_show.*

class TvShowFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_tv_show, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        if (activity != null){
            val viewModel = ViewModelProvider(this, ViewModelProvider.NewInstanceFactory())[TvShowViewModel::class.java]
            val tvShow = viewModel.getTvShow()

            val tvShowAdapter = TvShowAdapter()
            tvShowAdapter.setTvShow(tvShow)

            with(rec_tvshow){
                layoutManager = GridLayoutManager(context, 2, GridLayoutManager.VERTICAL, false)
                setHasFixedSize(true)
                adapter = tvShowAdapter
            }
        }
    }
}