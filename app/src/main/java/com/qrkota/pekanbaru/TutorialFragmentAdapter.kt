package com.qrkota.pekanbaru

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter

class TutorialFragmentAdapter(activity: FragmentActivity) : FragmentStateAdapter(activity) {
    override fun getItemCount(): Int = 3

    override fun createFragment(position: Int): Fragment = when (position) {
        0 -> Tutorial1Fragment()
        1 -> Tutorial2Fragment()
        else -> Tutorial3Fragment()
    }
}
