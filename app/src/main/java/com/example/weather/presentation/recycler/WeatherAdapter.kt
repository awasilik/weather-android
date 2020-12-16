package com.example.weather.presentation.recycler

import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.weather.R
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.weather_recycler_item.view.*

class WeatherAdapter() : RecyclerView.Adapter<WeatherAdapter.ViewHolder>() {

    val sampleList = listOf(
        Pair(12, Uri.parse("http://openweathermap.org/img/wn/10d@2x.png")),
        Pair(3, Uri.parse("http://openweathermap.org/img/wn/09d@2x.png")),
        Pair(25, Uri.parse("http://openweathermap.org/img/wn/04n@2x.png"))
    )

    inner class ViewHolder(val view: View) : RecyclerView.ViewHolder(view) {
        fun bind(data : Pair<Int, Uri>)
        {
            view.temperatureItemText.text = data.first.toString()
            Picasso.with(view.context).load(data.second).into(view.weatherItemImage)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.weather_recycler_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(sampleList[position])
    }

    override fun getItemCount() = sampleList.size
}