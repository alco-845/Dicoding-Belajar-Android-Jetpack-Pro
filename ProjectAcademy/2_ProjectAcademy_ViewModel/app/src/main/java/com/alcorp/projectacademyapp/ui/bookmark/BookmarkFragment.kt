package com.alcorp.projectacademyapp.ui.bookmark

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.app.ShareCompat
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.alcorp.projectacademyapp.R
import com.alcorp.projectacademyapp.data.CourseEntity
import com.alcorp.projectacademyapp.utils.DataDummy
import kotlinx.android.synthetic.main.fragment_bookmark.*

class BookmarkFragment : Fragment(), BookmarkFragmentCallback {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? = inflater.inflate(R.layout.fragment_bookmark, container, false)

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        if (activity != null){
            val viewModel = ViewModelProvider(this, ViewModelProvider.NewInstanceFactory())[BookmarkViewModel::class.java]
            val courses = viewModel.getBookmarks()
            val bookmarkAdapter = BookmarkAdapter(this)
            bookmarkAdapter.setCourse(courses)

            with(rv_bookmark){
                layoutManager = LinearLayoutManager(context)
                setHasFixedSize(true)
                adapter = bookmarkAdapter
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