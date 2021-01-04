package com.example.weather.ui.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.weather.R
import com.example.weather.domain.model.DailyForecast
import kotlinx.android.synthetic.main.daily_forecast_item.view.*
import java.time.format.DateTimeFormatter
import java.time.format.TextStyle
import java.util.*
import kotlin.collections.ArrayList

class DailyForecastAdapter : RecyclerView.Adapter<DailyForecastAdapter.ViewHolder>() {

    private var forecastDataList: List<DailyForecast> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.daily_forecast_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(forecastDataList[position])
    }

    override fun getItemCount() = forecastDataList.size

    fun updateForecast(newForecastDataList: List<DailyForecast>) {
        forecastDataList = newForecastDataList;
        notifyDataSetChanged()
    }

    inner class ViewHolder(private val view: View) : RecyclerView.ViewHolder(view) {
        fun bind(data: DailyForecast) {
            view.daily_tv_date.text = data.time.format(DateTimeFormatter.ofPattern("dd.MM"))
            view.daily_tv_weekDay.text = data.time.dayOfWeek.getDisplayName(TextStyle.FULL, Locale.getDefault())
            view.daily_tv_temperature.text = data.dayTemperature.toString()
        }
    }
}