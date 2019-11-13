package com.ddd.airplane.common.extension

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager

/**
 * Fragment 교체
 *
 * @param fragmentManager
 * @param replaceId
 */
fun Fragment.replace(fragmentManager: FragmentManager, replaceId: Int) {
    fragmentManager.beginTransaction().run {
        replace(replaceId, this@replace)
        commitNowAllowingStateLoss()
    }
}
