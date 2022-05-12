package com.example.stuffy.presentation.transaction

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.annotation.StringRes
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.viewpager2.widget.ViewPager2
import com.example.stuffy.R
import com.example.stuffy.core.ui.SectionsPagerAdapter
import com.example.stuffy.databinding.FragmentTransactionBinding
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator


class TransactionFragment : Fragment() {

    private var _binding: FragmentTransactionBinding? = null


    private val binding get() = _binding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): ConstraintLayout? {
        val notificationsViewModel =
            ViewModelProvider(this)[TransactionViewModel::class.java]

        _binding = FragmentTransactionBinding.inflate(inflater, container, false)
        val root: ConstraintLayout? = binding?.root

        val sectionsPagerAdapter = SectionsPagerAdapter(activity as AppCompatActivity)
        val viewPager: ViewPager2? = binding?.viewPager
        viewPager?.adapter = sectionsPagerAdapter
        val tabs: TabLayout? = binding?.tabs
        viewPager?.let {
            tabs?.let { it1 ->
                TabLayoutMediator(it1, it) { tab, position ->
                    tab.text = resources.getString(TAB_TITLES[position])
                }.attach()
            }
        }

        return root
    }
    companion object {
        @StringRes
        private val TAB_TITLES = intArrayOf(
            R.string.tab_text_1,
            R.string.tab_text_2,
            R.string.tab_text_3
        )
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}