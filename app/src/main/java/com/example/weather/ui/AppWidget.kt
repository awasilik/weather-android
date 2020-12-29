package com.example.weather.ui

import android.app.PendingIntent
import android.appwidget.AppWidgetManager
import android.appwidget.AppWidgetProvider
import android.content.Context
import android.content.Intent
import android.widget.RemoteViews
import com.example.weather.R
import com.example.weather.viewmodel.AppWidgetViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class AppWidget : AppWidgetProvider() {

    override fun onUpdate(
        context: Context?,
        appWidgetManager: AppWidgetManager?,
        appWidgetIds: IntArray?
    ) {
        val viewModel = AppWidgetViewModel()

        appWidgetIds?.forEach { widgetId ->
            val pendingIntent = Intent(context, HostActivity::class.java)
                .let { PendingIntent.getActivity(context, 0, it, 0)}

            CoroutineScope(Dispatchers.Main).launch {
                val temperature = viewModel.getTemperature()

                val views = RemoteViews(context?.packageName, R.layout.app_widget)
                views.setTextViewText(R.id.widget_tv_temperature, temperature.toString())
                views.setTextViewText(R.id.widget_tv_city, context?.getString(viewModel.location.labelId))
                views.setOnClickPendingIntent(R.id.widget_root, pendingIntent)

                appWidgetManager?.updateAppWidget(widgetId, views)
            }
        }
    }
}