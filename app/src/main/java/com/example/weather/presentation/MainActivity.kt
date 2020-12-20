package com.example.weather.presentation

import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.weather.R
import com.example.weather.domain.Location
import com.example.weather.domain.MainViewModel
import com.example.weather.presentation.recycler.WeatherAdapter
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private val viewModel = MainViewModel()

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
        citySpinner.adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, viewModel.locationList)
        citySpinner.onItemSelectedListener = this.SpinnerItemSelectedListener()

        refreshButton.setOnClickListener { update() }

        forecast_recycler.adapter = WeatherAdapter(viewModel.forecast)
        forecast_recycler.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
    }

    private fun update()
    {
        //viewModel.refreshData()
    }

    inner class SpinnerItemSelectedListener : AdapterView.OnItemSelectedListener
    {
        override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
            viewModel.currentLocation = parent?.getItemAtPosition(position) as Location
        }

        override fun onNothingSelected(parent: AdapterView<*>?) {
        }
    }
}
