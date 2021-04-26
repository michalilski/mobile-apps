package com.example.gallerytab

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.gallerytab.firstpage.FirstPageFragment
import com.example.gallerytab.secondpage.SecondPageFragment

class FragmentAdapter(fragmentManager: FragmentManager, lifeCycle: Lifecycle) :
    FragmentStateAdapter(fragmentManager, lifeCycle) {
    override fun getItemCount(): Int {
        return 2
    }

    override fun createFragment(position: Int): Fragment {
        when (position) {
            1 -> return SecondPageFragment()
        }
        return FirstPageFragment()
    }
}