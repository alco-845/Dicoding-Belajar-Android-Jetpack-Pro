package com.alcorp.moviecatalogue.view.home

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.alcorp.moviecatalogue.R
import kotlinx.android.synthetic.main.activity_home.*

class HomeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        val sectionPagerAdapter = SectionPagerAdapter(this, supportFragmentManager)
        view_pager.adapter = sectionPagerAdapter
        tabs.setupWithViewPager(view_pager)

        supportActionBar?.elevation = 0f
    }
}