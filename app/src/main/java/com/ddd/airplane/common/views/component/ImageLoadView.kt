package com.ddd.airplane.common.views.component

import android.annotation.SuppressLint
import android.content.Context
import android.content.res.TypedArray
import android.graphics.drawable.Drawable
import android.util.AttributeSet
import androidx.appcompat.widget.AppCompatImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.RequestOptions
import com.bumptech.glide.request.target.Target
import com.ddd.airplane.R
import com.ddd.airplane.common.utils.tryCatch

/**
 * ImageLoadView
 *
 * @author jess
 */
class ImageLoadView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : AppCompatImageView(context, attrs, defStyleAttr) {

    enum class Style { NORMAL, CIRCLE, ROUND }

    private val glide by lazy {
        Glide.with(context)
    }

    init {
        intLayout(attrs, defStyleAttr)
    }

    @SuppressLint("CustomViewStyleable", "Recycle", "CheckResult")
    private fun intLayout(
        attrs: AttributeSet?,
        defStyleAttr: Int
    ) {
        if (attrs != null) {
            val typedValue = context.obtainStyledAttributes(
                attrs,
                R.styleable.ImageLoadView,
                defStyleAttr,
                0
            )

            glide.load(typedValue.getString(R.styleable.ImageLoadView_url)).run {

                // 플레이스 홀더
                placeholder(typedValue.getDrawable(R.styleable.ImageLoadView_placeholder))

                // 에러
                error(typedValue.getDrawable(R.styleable.ImageLoadView_error))

                // fade 처리
                transition(DrawableTransitionOptions.withCrossFade())

                // Request Options
                apply(getRequestOptions(typedValue))

                // 이미지
                into(this@ImageLoadView)

                // 리스너
                listener(object : RequestListener<Drawable> {
                    override fun onLoadFailed(
                        e: GlideException?,
                        model: Any?,
                        target: Target<Drawable>?,
                        isFirstResource: Boolean
                    ): Boolean {

                        return false
                    }

                    override fun onResourceReady(
                        resource: Drawable?,
                        model: Any?,
                        target: Target<Drawable>?,
                        dataSource: DataSource?,
                        isFirstResource: Boolean
                    ): Boolean {
                        return false
                    }
                })
            }
        }
    }

    /**
     * Options
     */
    private fun getRequestOptions(typedValue: TypedArray): RequestOptions {

        var result = RequestOptions().centerCrop()

        tryCatch {

            // 스타일
            val style = Style.values()[typedValue.getInt(
                R.styleable.ImageLoadView_style,
                Style.NORMAL.ordinal
            )]

//            val corner = typedValue.getInt(R.styleable.ImageLoadView_corner, 0)
//
//            result = when (style) {
//                Style.CIRCLE -> {
//                    RequestOptions().centerCrop().circleCrop()
//                }
//
//                Style.ROUND -> {
//                    RequestOptions.bitmapTransform(RoundedCorners(corner))
//                }
//
//                else -> {
//                    RequestOptions().centerCrop()
//                }
//            }
        }

        return result
    }
}