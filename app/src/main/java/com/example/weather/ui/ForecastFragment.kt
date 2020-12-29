package com.example.weather.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.weather.R
import com.example.weather.databinding.ForecastFragmentBinding
import com.example.weather.viewmodel.ForecastViewModel

class ForecastFragment : Fragment(){

    private lateinit var binding: ForecastFragmentBinding

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
}