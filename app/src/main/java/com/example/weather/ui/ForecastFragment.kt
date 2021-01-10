package com.example.weather.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.weather.databinding.ForecastFragmentBinding
import com.example.weather.domain.model.DailyForecast
import com.example.weather.ui.adapters.DailyForecastAdapter
import com.example.weather.ui.adapters.DailyForecastClickListener
import com.example.weather.viewmodel.ForecastViewModel
import com.squareup.picasso.Picasso
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.forecast_fragment.*
import java.time.format.DateTimeFormatter
import java.time.format.TextStyle
import java.util.*

@AndroidEntryPoint
class ForecastFragment : Fragment(), DailyForecastClickListener{

    private lateinit var binding: ForecastFragmentBinding
    private val viewModel: ForecastViewModel by viewModels()
    private val dailyForecastAdapter = DailyForecastAdapter(this)

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = ForecastFragmentBinding.inflate(inflater, container, false).apply {
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

    override fun itemSelected(forecast: DailyForecast) {
        updateForecast(forecast)
    }

    private fun setupObservers() {
        binding.viewmodel?.forecast?.observe(binding.lifecycleOwner!!) {
            dailyForecastAdapter.updateForecast(it) }
    }

    private fun setupView() {
        forecast_recycler.adapter = dailyForecastAdapter
        forecast_recycler.layoutManager = LinearLayoutManager(context)
    }

    private fun updateForecast(forecast: DailyForecast){
        forecast_tv_weekDay.text = forecast.time.dayOfWeek.getDisplayName(TextStyle.FULL, Locale.getDefault())
        forecast_tv_date.text = forecast.time.format(DateTimeFormatter.ofPattern("dd.MM"))
        forecast_tv_temperature_day.text = forecast.dayTemperature.toString()
        forecast_tv_temperature_night.text = forecast.nightTemperature.toString()

        Picasso.get().load(forecast.imageUrl).into(forecast_img_weather)
    }
}