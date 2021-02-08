package com.elke.weather.ui.presentation

import android.app.PendingIntent
import android.appwidget.AppWidgetManager
import android.appwidget.AppWidgetProvider
import android.content.Context
import android.content.Intent
import android.widget.RemoteViews
import com.elke.weather.R
import com.elke.weather.ui.viewmodel.AppWidgetViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class AppWidget : AppWidgetProvider() {

    @Inject lateinit var viewModel: AppWidgetViewModel

    override fun onUpdate(
        context: Context?,
        appWidgetManager: AppWidgetManager?,
        appWidgetIds: IntArray?
    ) {
        appWidgetIds?.forEach { widgetId ->
            val pendingIntent = Intent(context, HostActivity::class.java)
                .let { PendingIntent.getActivity(context, 0, it, 0)}

            CoroutineScope(Dispatchers.Main).launch {
                val currentWeather = viewModel.getCurrentWeather()

                currentWeather?.let {
                    val views = RemoteViews(context?.packageName, R.layout.app_widget)
                    views.setTextViewText(R.id.widget_tv_temperature, it.temperature.toString())
                    views.setTextViewText(R.id.widget_tv_city, context?.getString(viewModel.location.labelId))
                    views.setOnClickPendingIntent(R.id.widget_root, pendingIntent)

                    appWidgetManager?.updateAppWidget(widgetId, views)
                }
            }
        }
    }
}