package com.example.weather.ui.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.weather.R
import com.example.weather.domain.model.HourlyWeather
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.hourly_forecast_item.view.*

class HourlyForecastAdapter: RecyclerView.Adapter<HourlyForecastAdapter.ViewHolder>() {

    private var forecastDataList: List<HourlyWeather> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.hourly_forecast_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(forecastDataList[position])
    }

    override fun getItemCount() = forecastDataList.size

    fun updateForecast(newForecastDataList: List<HourlyWeather>) {
        forecastDataList = newForecastDataList;
        notifyDataSetChanged()
    }

    inner class ViewHolder(private val view: View) : RecyclerView.ViewHolder(view) {
        fun bind(data: HourlyWeather) {
            view.hourly_tv_time.text = "${data.time.hour}:00"
            view.hourly_tv_temperature.text = data.temperature.toString()
            Picasso.get().load(data.imageUrl).into(view.hourly_img_weather_icon)
        }
    }
}