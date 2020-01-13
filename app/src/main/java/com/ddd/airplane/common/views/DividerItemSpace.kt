package com.ddd.airplane.common.views

import android.graphics.Rect
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.ddd.airplane.common.utils.tryCatch

/**
 * RecyclerView 간격
 * @property orientation
 */
class DividerItemSpace(
    private val orientation: Int? = VERTICAL,
    private val space: Int
) :
    RecyclerView.ItemDecoration() {

    companion object {
        const val HORIZONTAL = LinearLayoutManager.HORIZONTAL
        const val VERTICAL = LinearLayoutManager.VERTICAL
    }

    init {
        checkValid()
    }

    private fun checkValid() {
        require(!(orientation != HORIZONTAL && orientation != VERTICAL)) { "invalid orientation" }
    }

    override fun getItemOffsets(outRect: Rect, itemPosition: Int, parent: RecyclerView) {
        tryCatch {
            if (orientation == VERTICAL) {
                outRect.set(0, 0, 0, space)
            } else {
                outRect.set(0, 0, space, 0)
            }
        }
    }
}