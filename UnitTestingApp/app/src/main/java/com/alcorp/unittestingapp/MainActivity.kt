package com.alcorp.unittestingapp

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*

private lateinit var mainViewModel: MainViewModel

class MainActivity : AppCompatActivity(), View.OnClickListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mainViewModel = MainViewModel(CuboidModel())

        btn_save.setOnClickListener(this)
        btn_calculate_surface_area.setOnClickListener(this)
        btn_calculate_circumference.setOnClickListener(this)
        btn_calculate_volume.setOnClickListener(this)
    }

    override fun onClick(view: View) {
        val length = edt_length.text.toString()
        val width = edt_width.text.toString()
        val height = edt_height.text.toString()

        when {
            length.isEmpty() -> edt_length.error = "Field is empty"
            width.isEmpty() -> edt_width.error = "Field is empty"
            height.isEmpty() -> edt_height.error = "Field is empty"
            else -> {
                val l = length.toDouble()
                val w = width.toDouble()
                val h = height.toDouble()

                when {
                    view.id == R.id.btn_save -> {
                        mainViewModel.save(l, w, h)
                        visible()
                    }

                    view.id == R.id.btn_calculate_circumference -> {
                        tv_result.text = mainViewModel.getCircumference().toString()
                        gone()
                    }

                    view.id == R.id. btn_calculate_surface_area -> {
                        tv_result.text = mainViewModel.getSurfaceArea().toString()
                        gone()
                    }

                    view.id == R.id.btn_calculate_volume -> {
                        tv_result.text = mainViewModel.getVolume().toString()
                        gone()
                    }
                }
            }
        }
    }

    private fun visible(){
        btn_calculate_volume.visibility = View.VISIBLE
        btn_calculate_circumference.visibility = View.VISIBLE
        btn_calculate_surface_area.visibility = View.VISIBLE
        btn_save.visibility = View.GONE
    }

    private fun gone(){
        btn_calculate_volume.visibility = View.GONE
        btn_calculate_circumference.visibility = View.GONE
        btn_calculate_surface_area.visibility = View.GONE
        btn_save.visibility = View.VISIBLE
    }
}