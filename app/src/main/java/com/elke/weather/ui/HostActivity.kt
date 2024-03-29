package com.elke.weather.ui

import android.os.Bundle
import android.view.View
import android.view.WindowManager
import android.widget.AdapterView
import androidx.activity.viewModels
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.elke.weather.R
import com.elke.weather.databinding.HostActivityBinding
import com.elke.weather.domain.model.Location
import com.elke.weather.ui.adapters.HostPagerAdapter
import com.elke.weather.ui.adapters.LocationAdapter
import com.elke.weather.viewmodel.HostViewModel
import com.google.android.material.tabs.TabLayoutMediator
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.host_activity.*
import java.util.*

@AndroidEntryPoint
class HostActivity : AppCompatActivity() {

    private lateinit var binding: HostActivityBinding
    val viewModel: HostViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        window.setFlags(
            WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
            WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS
        )

        setupBinding()
        setupObservers()
        setupSpinner()
        setupViewPager()
    }

    private fun setupBinding() {
        binding = DataBindingUtil.setContentView(this, R.layout.host_activity)
        binding.viewmodel = viewModel
        binding.lifecycleOwner = this
    }

    private fun setupObservers() {
        binding.viewmodel?.errorMessage?.observe(binding.lifecycleOwner!!, {
            if (it != null)
                showProblemsDialog(it)
        })
    }

    private fun setupSpinner() {
        val spinnerAdapter = LocationAdapter(this, binding.viewmodel?.locationList!!)

        host_spn_city.adapter = spinnerAdapter
        host_spn_city.setSelection(spinnerAdapter.getPosition(binding.viewmodel?.currentLocation))
        host_spn_city.onItemSelectedListener = this.SpinnerItemSelectedListener()
    }

    private fun setupViewPager() {
        val pagerAdapter = HostPagerAdapter(this)
        pagerAdapter.addFragment(getString(R.string.fragment_weather), WeatherFragment())
        pagerAdapter.addFragment(getString(R.string.fragment_forecast), ForecastFragment())

        host_view_pager.isUserInputEnabled = false
        host_view_pager.adapter = pagerAdapter

        TabLayoutMediator(host_tab_layout, host_view_pager) { tab, position ->
            tab.text = pagerAdapter.getFragmentName(position)
        }.attach()
    }

    private fun showProblemsDialog(errorMessage: String) {
        AlertDialog.Builder(this)
            .setTitle("Something went wrong")
            .setMessage("Could not retrieve data, reason: $errorMessage")
            .setNegativeButton("Cancel") { dialog, _ -> dialog.dismiss() }
            .setPositiveButton("Retry") { dialog, _ ->
                binding.viewmodel?.refreshData()
                dialog.dismiss()
            }
            .show()
    }

    inner class SpinnerItemSelectedListener : AdapterView.OnItemSelectedListener {
        override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
            binding.viewmodel?.currentLocation = parent?.getItemAtPosition(position) as Location
        }

        override fun onNothingSelected(parent: AdapterView<*>?) {
        }
    }
}
