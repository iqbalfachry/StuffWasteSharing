package com.example.stuffy.presentation.adapter

import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.stuffy.presentation.transaction.TransactionConfirmationFragment
import com.example.stuffy.presentation.transaction.TransactionShareFragment
import com.example.stuffy.presentation.transaction.TransactionTakeFragment

class SectionsPagerAdapter(activity: AppCompatActivity) : FragmentStateAdapter(activity) {
    override fun createFragment(position: Int): Fragment {
        var fragment: Fragment? = null
        when (position) {
            0 -> fragment = TransactionConfirmationFragment()
            1 -> fragment = TransactionShareFragment()
            2 -> fragment = TransactionTakeFragment()
        }
        return fragment as Fragment
    }

    override fun getItemCount(): Int {
        return 3
    }
}