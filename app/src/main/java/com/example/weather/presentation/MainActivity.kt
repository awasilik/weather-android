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
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.weather_recycler_item.view.*
import kotlin.math.roundToInt

class MainActivity : AppCompatActivity() {

    private val viewModel = MainViewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initView()
    }

    private fun initView()
    {
        viewModel.refreshData()

        citySpinner.adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, viewModel.locationList)
        citySpinner.onItemSelectedListener = this.SpinnerItemSelectedListener()

        refreshButton.setOnClickListener { viewModel.refreshData() }

        forecast_recycler.adapter = WeatherAdapter(viewModel.forecast)
        forecast_recycler.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)

        Picasso.get().load(viewModel.currentWeather?.imageUrl).into(weatherImage)

        temperatureValueText.text = viewModel.currentWeather?.temperature?.roundToInt().toString()
        windText.text = viewModel.currentWeather?.windSpeed?.toWindString()
        pressureText.text = viewModel.currentWeather?.pressure?.toPressureString()
        humidityText.text = viewModel.currentWeather?.humidity?.toHumidityString()
    }

    private fun Double.toWindString() = "$this m/s"

    private fun Int.toPressureString() = "$this hPa"

    private fun Int.toHumidityString() = "$this %"

    inner class SpinnerItemSelectedListener : AdapterView.OnItemSelectedListener
    {
        override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
            viewModel.currentLocation = parent?.getItemAtPosition(position) as Location
            viewModel.refreshData()
        }

        override fun onNothingSelected(parent: AdapterView<*>?) {
        }
    }
}
