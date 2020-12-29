package com.example.weather.ui.adapters

import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter

class HostPagerAdapter(activity: AppCompatActivity) : FragmentStateAdapter(activity) {

    private val fragments = ArrayList<Pair<String, Fragment>>()

    fun addFragment(fragmentName: String, fragment: Fragment){
        fragments.add(Pair(fragmentName, fragment))
    }

    fun getFragmentName(position: Int) = fragments[position].first

    override fun getItemCount() = fragments.size

    override fun createFragment(position: Int) = fragments[position].second
}