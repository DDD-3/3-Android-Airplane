package com.ddd.airplane.common.views.component

import android.content.Context
import android.util.AttributeSet
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import timber.log.Timber

class BoundRecyclerView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : RecyclerView(context, attrs, defStyleAttr) {

    private var listener: (() -> Unit)? = null
    private var lastVisiblePosition = -1
    private var listSize = 0
    private var isLoaded = false

    init {
        initialize()
    }

    fun setOnBoundListener(listener: (() -> Unit)?) {
        this.listener = listener
    }

    private fun initialize() {

        this.layoutManager = LinearLayoutManager(context)

//        isFocusable = false
//        isFocusableInTouchMode = false
//        isNestedScrollingEnabled = false

        addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)

                val totalItemCount = layoutManager!!.itemCount
                if (totalItemCount == 0 || dy == 0) return

                lastVisiblePosition =
                    (layoutManager as LinearLayoutManager).findLastVisibleItemPosition()

                if (!isLoaded && totalItemCount <= lastVisiblePosition + 2) {
                    listener?.let {
                        Timber.d(">> Bound")
                        isLoaded = true
                        it.invoke()
                    }
                }
            }
        })
    }

    override fun setAdapter(adapter: RecyclerView.Adapter<*>?) {
        if (adapter != null) {
            try {
                adapter.registerAdapterDataObserver(object :
                    RecyclerView.AdapterDataObserver() {
                    override fun onChanged() {
                        super.onChanged()
                        isLoaded = false
                        listSize = adapter.itemCount
                    }
                })
            } catch (e: Exception) {
                e.printStackTrace()
            }

        }
        super.setAdapter(adapter)
    }
}
