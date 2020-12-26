package com.example.weather.presentation

import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.weather.R
import com.example.weather.domain.Location
import com.example.weather.domain.WeatherViewModel
import com.example.weather.presentation.recycler.ForecastAdapter
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.weather_activity.*

class WeatherActivity : AppCompatActivity() {

    private val viewModel = WeatherViewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.weather_activity)

        initView()
    }

    private fun initView()
    {
        viewModel.refreshData()

        weather_swipe_refresh.setOnRefreshListener { viewModel.refreshData() }

        weather_spn_city.adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, viewModel.locationList)
        weather_spn_city.onItemSelectedListener = this.SpinnerItemSelectedListener()

        weather_recycler_forecast.adapter = ForecastAdapter(viewModel.forecast)
        weather_recycler_forecast.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)

        Picasso.get().load(viewModel.currentWeather?.imageUrl).into(weather_img_weather_icon)

        weather_tv_temperature.text = viewModel.currentWeather?.temperature?.toString()
        weather_tv_wind.text = viewModel.currentWeather?.windSpeed?.toWindString()
        weather_tv_pressure.text = viewModel.currentWeather?.pressure?.toPressureString()
        weather_tv_humidity.text = viewModel.currentWeather?.humidity?.toHumidityString()
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
