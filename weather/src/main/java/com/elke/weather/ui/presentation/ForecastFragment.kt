package com.elke.weather.ui.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.elke.weather.databinding.ForecastFragmentBinding
import com.elke.weather.ui.presentation.adapters.DailyForecastAdapter
import com.elke.weather.ui.viewmodel.ForecastViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.forecast_fragment.*

@AndroidEntryPoint
class ForecastFragment : Fragment() {

    private lateinit var binding: ForecastFragmentBinding
    private val viewModel: ForecastViewModel by viewModels()
    private val dailyForecastAdapter = DailyForecastAdapter()

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

    private fun setupObservers() {
        binding.viewmodel?.forecast?.observe(binding.lifecycleOwner!!) {
            dailyForecastAdapter.updateForecast(it) }
    }

    private fun setupView() {
        forecast_recycler.adapter = dailyForecastAdapter
        forecast_recycler.layoutManager = LinearLayoutManager(context)
    }
}