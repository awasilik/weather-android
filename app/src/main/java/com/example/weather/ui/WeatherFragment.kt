package com.example.weather.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.weather.databinding.WeatherFragmentBinding
import com.example.weather.ui.adapters.HourlyForecastAdapter
import com.example.weather.viewmodel.WeatherViewModel
import com.squareup.picasso.Picasso
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.weather_fragment.*
import java.time.format.DateTimeFormatter

@AndroidEntryPoint
class WeatherFragment : Fragment() {

    private lateinit var binding: WeatherFragmentBinding
    private val viewModel: WeatherViewModel by viewModels()
    private val hourlyForecastAdapter = HourlyForecastAdapter()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = WeatherFragmentBinding.inflate(inflater, container, false).apply {
            viewmodel = viewModel
            lifecycleOwner = viewLifecycleOwner
        }

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupObservers()
        setupView()
    }

    private fun setupObservers() {
        binding.viewmodel?.let { vm ->
            vm.hourlyForecast.observe(binding.lifecycleOwner!!, {
                hourlyForecastAdapter.updateForecast(it)
            })

            vm.weather.observe(binding.lifecycleOwner!!, {
                weather_tv_time.text = it.time.format(DateTimeFormatter.ofPattern("HH:mm"))
                Picasso.get().load(it.imageUrl).into(weather_img_weather_icon)
            })
        }
    }

    private fun setupView() {
        val layoutManager = LinearLayoutManager(this.context, LinearLayoutManager.HORIZONTAL, false)
        val itemDecoration = DividerItemDecoration(this.context, layoutManager.orientation)

        weather_recycler_forecast.adapter = hourlyForecastAdapter
        weather_recycler_forecast.layoutManager = layoutManager
        weather_recycler_forecast.addItemDecoration(itemDecoration)
    }
}