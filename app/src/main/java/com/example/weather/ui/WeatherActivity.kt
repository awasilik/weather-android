package com.example.weather.ui

import android.os.Bundle
import android.view.View
import android.view.WindowManager
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.weather.R
import com.example.weather.databinding.WeatherActivityBinding
import com.example.weather.domain.Location
import com.example.weather.ui.recycler.ForecastAdapter
import com.example.weather.viewmodel.WeatherViewModel
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.weather_activity.*


class WeatherActivity : AppCompatActivity() {

    private lateinit var binding: WeatherActivityBinding
    private lateinit var forecastAdapter: ForecastAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        window.setFlags(
            WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
            WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS
        )

        setupBinding()
        setupObservers()
        setupView()
    }

    private fun setupBinding() {
        binding = DataBindingUtil.setContentView(this, R.layout.weather_activity)
        binding.viewmodel = ViewModelProvider(this).get(WeatherViewModel::class.java)
        binding.lifecycleOwner = this
    }

    private fun setupObservers() {
        binding.viewmodel?.let { vm ->
            vm.forecast.observe(binding.lifecycleOwner!!, {
                forecastAdapter.updateForecast(it)
            })

            vm.weather.observe(binding.lifecycleOwner!!, {
                Picasso.get().load(it.imageUrl).into(weather_img_weather_icon)
            })
        }
    }

    private fun setupView() {
        val spinnerAdapter = ArrayAdapter(
            this,
            R.layout.location_spinner_item,
            binding.viewmodel?.locationList!!
        )
        spinnerAdapter.setDropDownViewResource(R.layout.location_spinner_dropdown_item)
        weather_spn_city.adapter = spinnerAdapter
        weather_spn_city.setSelection(spinnerAdapter.getPosition(binding.viewmodel?.currentLocation?.value))
        weather_spn_city.onItemSelectedListener = this.SpinnerItemSelectedListener()

        forecastAdapter = ForecastAdapter(emptyList())
        val layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        val itemDecoration = DividerItemDecoration(this, layoutManager.orientation)

        weather_recycler_forecast.adapter = forecastAdapter
        weather_recycler_forecast.layoutManager = layoutManager
        weather_recycler_forecast.addItemDecoration(itemDecoration)
    }

    inner class SpinnerItemSelectedListener : AdapterView.OnItemSelectedListener {
        override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
            binding.viewmodel?.currentLocation?.value =
                parent?.getItemAtPosition(position) as Location
            binding.viewmodel?.refreshData()
        }

        override fun onNothingSelected(parent: AdapterView<*>?) {
        }
    }
}
