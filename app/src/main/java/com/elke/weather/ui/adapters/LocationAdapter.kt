package com.elke.weather.ui.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView
import com.elke.weather.R
import com.elke.weather.domain.model.Location
import com.elke.weather.domain.model.getName

class LocationAdapter(
    context: Context,
    private val locations: Array<Location>,
    private val itemLayoutId: Int = R.layout.location_spinner_item)
    : ArrayAdapter<Location>(context, itemLayoutId, locations) {

    private val dropdownLayoutId = R.layout.location_spinner_dropdown_item

    override fun getView(position: Int, convertView: View?, parent: ViewGroup) =
        createViewFromResource(position, convertView, parent, itemLayoutId)

    override fun getDropDownView(position: Int, convertView: View?, parent: ViewGroup?) =
        createViewFromResource(position, convertView, parent, dropdownLayoutId)

    private fun createViewFromResource(position: Int, convertView: View?, parent: ViewGroup?, layoutId: Int): View{
        val view = convertView as TextView? ?:
            LayoutInflater.from(context).inflate(layoutId, parent, false) as TextView
        view.text = locations[position].getName(context)
        return view
    }
}