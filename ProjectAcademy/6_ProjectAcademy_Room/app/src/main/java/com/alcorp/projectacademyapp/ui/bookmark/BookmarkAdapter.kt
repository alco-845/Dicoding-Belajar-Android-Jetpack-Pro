package com.alcorp.projectacademyapp.ui.bookmark

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.alcorp.projectacademyapp.R
import com.alcorp.projectacademyapp.data.source.local.entity.CourseEntity
import com.alcorp.projectacademyapp.ui.detail.DetailCourseActivity
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import kotlinx.android.synthetic.main.items_bookmark.view.*

class BookmarkAdapter(private val callback: BookmarkFragmentCallback) : RecyclerView.Adapter<BookmarkAdapter.CourseViewHolder>() {
    private var listCourse = ArrayList<CourseEntity>()
    
    fun setCourse(courses: List<CourseEntity>?){
        if(courses == null) return
        listCourse.clear()
        listCourse.addAll(courses)
    }
    
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BookmarkAdapter.CourseViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.items_bookmark, parent, false)
        return CourseViewHolder(view)
    }

    override fun onBindViewHolder(holder: BookmarkAdapter.CourseViewHolder, position: Int) {
        val course = listCourse[position]
        holder.bind(course, callback)
    }

    override fun getItemCount(): Int = listCourse.size

    class CourseViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(course: CourseEntity, callback: BookmarkFragmentCallback){
            with(itemView){
                tv_item_title.text = course.title
                tv_item_description.text = course.description
                tv_item_date.text = resources.getString(R.string.deadline_date, course.deadline)
                setOnClickListener {
                    val intent = Intent(context, DetailCourseActivity::class.java)
                    intent.putExtra(DetailCourseActivity.EXTRA_COURSE, course.courseId)
                    itemView.context.startActivity(intent)
                }
                img_share.setOnClickListener { callback.onShareClick(course) }
                Glide.with(context)
                        .load(course.imagePath)
                        .apply(RequestOptions.placeholderOf(R.drawable.ic_loading)
                                .error(R.drawable.ic_error))
                        .into(img_poster)
            }
        }
    }
}