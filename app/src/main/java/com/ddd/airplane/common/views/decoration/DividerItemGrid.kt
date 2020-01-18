package com.ddd.airplane.common.views.decoration

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import timber.log.Timber

/**
 * Grid Layout 간격 조절
 *
 * @property spanCount
 * @property space
 */
class DividerItemGrid(private val spanCount: Int = 2, private val space: Int) :
    RecyclerView.ItemDecoration() {

    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {

        val maxCount = parent.adapter?.itemCount ?: 0
        val position = parent.getChildAdapterPosition(view)
        val column = position % spanCount
        val row = position / spanCount
        val lastRow = (maxCount - 1) / spanCount

        Timber.d("jess : column $column")
        Timber.d("jess : row $row")

        outRect.left = column * space / spanCount
        outRect.right = space - (column + 1) * space / spanCount

        if (row > 0) {
            outRect.top = space
        }

        if (row == lastRow) {
            outRect.bottom = space
        }
    }

}


