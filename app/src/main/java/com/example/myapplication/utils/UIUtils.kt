package com.example.myapplication.utils

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager

class UIUtils {
    companion object {
        fun replaceFragmentAndAddToBackTrace(fragmentManager: FragmentManager, newFragment: Fragment,  fragmentId: Int, tag: String?) {
            val transaction = fragmentManager.beginTransaction()
            transaction.replace(fragmentId, newFragment, tag)
            transaction.addToBackStack(tag)
            transaction.commitAllowingStateLoss()
        }
    }
}