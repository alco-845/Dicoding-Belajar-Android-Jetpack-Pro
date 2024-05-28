package com.alcorp.projectacademyapp.ui.bookmark

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.app.ShareCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.alcorp.projectacademyapp.R
import com.alcorp.projectacademyapp.data.CourseEntity
import com.alcorp.projectacademyapp.utils.viewmodel.ViewModelFactory
import kotlinx.android.synthetic.main.fragment_bookmark.*

class BookmarkFragment : Fragment(), BookmarkFragmentCallback {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? = inflater.inflate(R.layout.fragment_bookmark, container, false)

    @SuppressLint("FragmentLiveDataObserve")
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        if (activity != null){
            val factory = ViewModelFactory.getInstance(requireActivity())
            val viewModel = ViewModelProvider(this, factory)[BookmarkViewModel::class.java]

            val adapter = BookmarkAdapter(this)
            progress_bar.visibility = View.VISIBLE
            viewModel.getBookmarks().observe(this, Observer { courses ->
                progress_bar.visibility = View.GONE
                adapter.setCourse(courses)
                adapter.notifyDataSetChanged()
            })

            with(rv_bookmark){
                layoutManager = LinearLayoutManager(context)
                setHasFixedSize(true)
                this.adapter = adapter
            }
        }
    }

    @SuppressLint("StringFormatInvalid")
    override fun onShareClick(course: CourseEntity) {
        if (activity != null){
            val mimeType = "text/plain"
            ShareCompat.IntentBuilder.from(requireActivity()).apply {
                setType(mimeType)
                setChooserTitle("Share this application now")
                setText(resources.getString(R.string.share_text, course.title))
                startChooser()
            }
        }
    }
}