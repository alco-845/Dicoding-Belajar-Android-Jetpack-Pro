package com.alcorp.projectacademyapp.ui.academy

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
import kotlinx.android.synthetic.main.items_academy.view.*

class AcademyAdapter : RecyclerView.Adapter<AcademyAdapter.CourseViewHolder>() {
    private var listCourse = ArrayList<CourseEntity>()

    fun setCourse(courses: List<CourseEntity>?){
        if (courses == null) return
        listCourse.clear()
        listCourse.addAll(courses)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CourseViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.items_academy, parent, false)
        return CourseViewHolder(view)
    }

    override fun onBindViewHolder(holder: CourseViewHolder, position: Int) {
        val course = listCourse[position]
        holder.bind(course)
    }

    override fun getItemCount(): Int = listCourse.size

    class CourseViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(course: CourseEntity){
            with(itemView){
                tv_item_title.text = course.title
                tv_item_description.text = course.description
                tv_item_date.text = resources.getString(R.string.deadline_date, course.deadline)
                setOnClickListener {
                    val intent = Intent(context, DetailCourseActivity::class.java)
                    intent.putExtra(DetailCourseActivity.EXTRA_COURSE, course.courseId)
                    itemView.context.startActivity(intent)
                }
                Glide.with(context)
                        .load(course.imagePath)
                        .apply(RequestOptions.placeholderOf(R.drawable.ic_loading)
                                .error(R.drawable.ic_error))
                        .into(img_poster)
            }
        }
    }
}