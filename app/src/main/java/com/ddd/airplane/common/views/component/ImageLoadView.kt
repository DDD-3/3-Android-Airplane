package com.ddd.airplane.common.views.component

import android.content.Context
import android.graphics.drawable.Drawable
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.FrameLayout
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import com.ddd.airplane.databinding.ImageLoadViewBinding
import kotlinx.android.synthetic.main.image_load_view.view.*
import timber.log.Timber

/**
 *
 * ImageLoaderView
 *
 * @author jess
 */
class ImageLoadView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : FrameLayout(context, attrs, defStyleAttr) {

    private var binding = ImageLoadViewBinding.inflate(LayoutInflater.from(context), this, true)

//    enum class Style { NORMAL, CIRCLE, ROUND }

//    init {
//        intLayout(attrs, defStyleAttr)
//    }
//
//    @SuppressLint("CustomViewStyleable", "Recycle", "CheckResult")
//    private fun intLayout(
//        attrs: AttributeSet?,
//        defStyleAttr: Int
//    ) {
//        if (attrs != null) {
//            val typedValue = context.obtainStyledAttributes(
//                attrs,
//                R.styleable.ImageLoadView,
//                defStyleAttr,
//                0
//            )
//
//            val url = typedValue.getString(R.styleable.ImageLoadView_url)
//            if (url.isNullOrEmpty()) {
//                binding.isLoaded = false
//                return
//            }
//
//
//        }
//    }

    fun setUrl(url: String?) {
        url?.let {
            loadImage(it)
        }
    }

    fun loadImage(url: String) {
        Glide.with(context)
            .load(url)
            .transition(DrawableTransitionOptions.withCrossFade())
            .listener(object : RequestListener<Drawable> {

                override fun onResourceReady(
                    resource: Drawable?,
                    model: Any?,
                    target: Target<Drawable>?,
                    dataSource: DataSource?,
                    isFirstResource: Boolean
                ): Boolean {
                    binding.isLoaded = true
                    Timber.d("onResourceReady $url")
                    return false
                }

                override fun onLoadFailed(
                    e: GlideException?,
                    model: Any?,
                    target: Target<Drawable>?,
                    isFirstResource: Boolean
                ): Boolean {
                    binding.isLoaded = false
                    Timber.d("onLoadFailed $url")
                    Timber.d("onLoadFailed ${e?.message}")
                    return false
                }
            })
            .into(iv_succeed)
//        Glide.with(context).load(url).run {
//
//            //            // 플레이스 홀더
////            placeholder(typedValue.getDrawable(R.styleable.ImageLoadView_placeholder))
////
////            // 에러
////            error(typedValue.getDrawable(R.styleable.ImageLoadView_error))
//
//            // Request Options
////                apply(getRequestOptions(typedValue))
//
//            // fade 처리
//            transition(DrawableTransitionOptions.withCrossFade())
//
//            // 이미지
//            into(iv_succeed)
//
//            // 리스너
//            listener(object : RequestListener<Drawable> {
//
//                override fun onResourceReady(
//                    resource: Drawable?,
//                    model: Any?,
//                    target: Target<Drawable>?,
//                    dataSource: DataSource?,
//                    isFirstResource: Boolean
//                ): Boolean {
//                    Timber.e("onResourceReady")
//                    return false
//                }
//
//                override fun onLoadFailed(
//                    e: GlideException?,
//                    model: Any?,
//                    target: Target<Drawable>?,
//                    isFirstResource: Boolean
//                ): Boolean {
//                    Timber.e("onLoadFailed")
//                    binding.isLoaded = false
//                    return true
//                }
//            })
//        }
    }
}