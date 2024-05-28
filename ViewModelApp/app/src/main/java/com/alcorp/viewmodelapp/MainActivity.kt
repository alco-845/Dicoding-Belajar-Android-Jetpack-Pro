package com.alcorp.viewmodelapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import kotlinx.android.synthetic.main.activity_main.*

private lateinit var viewModel: MainViewModel

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewModel = ViewModelProvider(this)[MainViewModel::class.java]

        displayResult()

        btn_calculate.setOnClickListener {
            val width = edt_width.text.toString()
            val height = edt_height.text.toString()
            val lenght = edt_lenght.text.toString()

            when {
                width.isEmpty() -> {
                    edt_width.error = "Is empty"
                }

                height.isEmpty() -> {
                    edt_height.error = "Is empty"
                }

                lenght.isEmpty() -> {
                    edt_lenght.error = "Is empty"
                }

                else -> {
                    viewModel.calculate(width, height, lenght)
                    displayResult()
                }
            }
        }
    }

    private fun displayResult() {
        tv_result.text = viewModel.result.toString()
    }
}