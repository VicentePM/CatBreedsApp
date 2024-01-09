package com.example.catbreedsapp.data.adapters

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.catbreedsapp.ui.fragments.SecondFragment
import com.example.catbreedsapp.ui.fragments.infoFragment
import com.example.catbreedsapp.ui.fragments.statsFragment

class viewPagerAdapter(activity: SecondFragment) : FragmentStateAdapter(activity) {
    override fun getItemCount(): Int {
        return 2
    }

    override fun createFragment(position: Int): Fragment {
        return if (position == 1) statsFragment() else infoFragment()
    }

}

