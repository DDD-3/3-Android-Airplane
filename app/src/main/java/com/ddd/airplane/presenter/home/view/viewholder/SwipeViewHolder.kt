package com.ddd.airplane.presenter.home.view.viewholder

import android.content.Context
import android.os.Handler
import android.view.View
import android.widget.LinearLayout
import androidx.core.view.ViewCompat
import androidx.databinding.ViewDataBinding
import androidx.viewpager2.widget.ViewPager2
import androidx.viewpager2.widget.ViewPager2.ORIENTATION_HORIZONTAL
import com.ddd.airplane.R
import com.ddd.airplane.common.base.BaseRecyclerViewAdapter
import com.ddd.airplane.common.base.BaseViewHolder
import com.ddd.airplane.common.utils.tryCatch
import com.ddd.airplane.data.response.home.BannerData
import com.ddd.airplane.data.response.home.HomeData
import com.ddd.airplane.databinding.HomeSwipeBannerItemBinding
import kotlinx.android.synthetic.main.home_swipe_banner.view.*
import timber.log.Timber


class SwipeViewHolder(
    private val context: Context?,
    viewDataBinding: ViewDataBinding
) : BaseViewHolder<HomeData.ItemData<Any>>(viewDataBinding) {

    private val bannerList = ArrayList<BannerData>()

    override fun onBind(item: HomeData.ItemData<Any>?) {
        tryCatch {
            initData(item)
            initLayout()
        }
    }

    private fun initData(item: HomeData.ItemData<Any>?) {
        tryCatch {
            item?.let {
                val list = it.item as ArrayList<BannerData>
                bannerList.run {
                    addAll(list)
                }
                Timber.d(bannerList.toString())
            }
        }
    }

    private fun initLayout() {

        setIndicator()

        // viewPager
        view.vp_banner.apply {

            offscreenPageLimit = 3

            // pager
            val pagerAdapter = object :
                BaseRecyclerViewAdapter<BannerData, HomeSwipeBannerItemBinding>(R.layout.home_swipe_banner_item) {

            }
            adapter = pagerAdapter
            pagerAdapter.addAllItem(bannerList)

            registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {

                override fun onPageScrolled(
                    position: Int,
                    positionOffset: Float,
                    positionOffsetPixels: Int
                ) {
                    super.onPageScrolled(position, positionOffset, positionOffsetPixels)
                }

                override fun onPageSelected(position: Int) {
                    setTitle(position)
                    setIndicator(position)
                }

                override fun onPageScrollStateChanged(state: Int) {
                    super.onPageScrollStateChanged(state)
                }
            })

            // 좌우 보이게 하기
            val pageMarginPx = resources.getDimensionPixelOffset(R.dimen.dp9)
            val offsetPx = resources.getDimensionPixelOffset(R.dimen.dp51)
            setPageTransformer { page, position ->
                val viewPager = page.parent.parent as ViewPager2
                val offset = position * -(2 * offsetPx + pageMarginPx)
                if (viewPager.orientation == ORIENTATION_HORIZONTAL) {
                    if (ViewCompat.getLayoutDirection(viewPager) == ViewCompat.LAYOUT_DIRECTION_RTL) {
                        page.translationX = -offset
                    } else {
                        page.translationX = offset
                    }
                } else {
                    page.translationY = offset
                }
            }
        }
    }

    /**
     * Dot 표시
     *
     */
    private fun setIndicator(position: Int = 0) {

        Handler().post {

            require(bannerList.isNotEmpty())

            val margie = context?.resources?.getDimensionPixelSize(R.dimen.dp2) ?: 0
            val size = context?.resources?.getDimensionPixelSize(R.dimen.dp8) ?: 0

            view.ll_indicator.removeAllViews()
            repeat(bannerList.size) {

                val dot = View(context)
                val params = LinearLayout.LayoutParams(
                    size,
                    size
                ).apply {
                    setMargins(margie, 0, margie, 0)
                }

                val drawable = if (it == position) {
                    R.drawable.shp_oval_brand_white
                } else {
                    R.drawable.shp_oval_36363e
                }
                dot.setBackgroundResource(drawable)

                view.ll_indicator.addView(dot, params)
            }
        }
    }

    /**
     * 타이틀 세팅
     *
     * @param position
     */
    private fun setTitle(position: Int) {
        require(bannerList.isNotEmpty())
        view.tv_title.text = bannerList[position].title
    }
}