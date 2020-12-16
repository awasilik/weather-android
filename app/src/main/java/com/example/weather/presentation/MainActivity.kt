package com.example.weather.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.weather.R
import com.example.weather.presentation.recycler.WeatherAdapter
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initView()
    }

    override fun onStart() {
        super.onStart()
        update()
    }

    private fun initView()
    {
        refreshButton.setOnClickListener { update() }

        forecast_recycler.adapter = WeatherAdapter()
        forecast_recycler.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
    }

    private fun update()
    {
    }
}
