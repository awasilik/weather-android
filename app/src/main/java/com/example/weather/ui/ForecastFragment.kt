package com.example.weather.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.weather.databinding.ForecastFragmentBinding
import com.example.weather.ui.adapters.DailyForecastAdapter
import com.example.weather.viewmodel.ForecastViewModel
import kotlinx.android.synthetic.main.forecast_fragment.*
import kotlinx.android.synthetic.main.weather_fragment.*

class ForecastFragment : Fragment(){

    private lateinit var binding: ForecastFragmentBinding
    private val dailyForecastAdapter = DailyForecastAdapter()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = ForecastFragmentBinding.inflate(inflater, container, false).apply {
            viewmodel = ViewModelProvider(this@ForecastFragment).get(ForecastViewModel::class.java)
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
        binding.viewmodel?.forecast?.observe(binding.lifecycleOwner!!) {
            dailyForecastAdapter.updateForecast(it) }
    }

    private fun setupView() {
        forecast_recycler.adapter = dailyForecastAdapter
        forecast_recycler.layoutManager = LinearLayoutManager(context)
    }
}