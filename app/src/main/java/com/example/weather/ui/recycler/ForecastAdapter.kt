package com.example.weather.ui.recycler

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.weather.R
import com.example.weather.domain.model.WeatherData
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.forecast_item.view.*

class ForecastAdapter(private var forecastDataList: List<WeatherData>) :
    RecyclerView.Adapter<ForecastAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.forecast_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(forecastDataList[position])
    }

    override fun getItemCount() = forecastDataList.size

    fun updateForecast(newForecastDataList: List<WeatherData>)
    {
        forecastDataList =  newForecastDataList;
        notifyDataSetChanged()
    }

    inner class ViewHolder(private val view: View) : RecyclerView.ViewHolder(view) {
        fun bind(data: WeatherData) {
            view.forecast_tv_time.text = "${data.time.hour}:00"
            view.forecast_tv_temperature.text = data.temperature.toString()
            Picasso.get().load(data.imageUrl).into(view.forecast_img_weather_icon)
        }
    }
}