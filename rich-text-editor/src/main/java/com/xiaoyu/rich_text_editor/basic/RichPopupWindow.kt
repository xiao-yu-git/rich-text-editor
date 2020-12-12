package com.xiaoyu.rich_text_editor.basic

import android.content.Context
import android.os.Build
import android.view.Gravity
import android.view.View
import android.view.ViewGroup
import android.widget.PopupWindow
import com.xiaoyu.rich_text_editor.R
import com.xiaoyu.rich_text_editor.drawable.PopupWindowBackground

/**
 * @author lmy
 * @Date 2020/11/30
 * base popup window
 */
class RichPopupWindow(view: View) : PopupWindow(view.context) {
    private val mContext: Context = view.context
    private val mView: View = view
    private val background: PopupWindowBackground = PopupWindowBackground(view)
    private val mWindowMargin: Int
    private val mContainerMargin: Int

    init {
        mWindowMargin = mContext.resources.getDimensionPixelOffset(R.dimen.window_margin)
        mContainerMargin =
            mContext.resources.getDimensionPixelOffset(R.dimen.tool_item_container_margin_window)
        initView()
    }

    private fun initView() {
        width = ViewGroup.LayoutParams.WRAP_CONTENT
        height = ViewGroup.LayoutParams.WRAP_CONTENT
        setBackgroundDrawable(background)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            elevation = 4f
        }
        contentView = mView
    }

    fun show(anchor: View) {
        val locations = IntArray(2)
        anchor.getLocationInWindow(locations)
        //计算 x 偏移量
        val viewWidth = viewWidth
        val anchorX = locations[0] + anchor.width / 2
        val centerViewX = viewWidth / 2
        var offsetX: Int
        offsetX = if (anchorX < centerViewX) {
            mWindowMargin
        } else {
            anchorX - centerViewX
        }
        if (offsetX < mWindowMargin) {
            offsetX = mWindowMargin
        }
        showAtLocation(
            anchor,
            Gravity.NO_GRAVITY,
            offsetX,
            locations[1] - viewHeight - mContainerMargin
        )
        //计算小尖尖的X位置 本质上是 anchor 的中间x坐标，但是要转换成 Background里的坐标
        background.setPointerX((anchorX - offsetX).toFloat())
        anchor.viewTreeObserver.addOnScrollChangedListener {
            if (isShowing) {
                dismiss()
            }
        }
    }

    private val viewHeight: Int
        get() {
            val height: Int = mView.height
            return if (height == 0) {
                mView.measure(
                    View.MeasureSpec.UNSPECIFIED,
                    View.MeasureSpec.UNSPECIFIED
                )
                mView.measuredHeight
            } else {
                height
            }
        }
    private val viewWidth: Int
        get() {
            val width: Int = mView.width
            return if (width == 0) {
                mView.measure(
                    View.MeasureSpec.UNSPECIFIED,
                    View.MeasureSpec.UNSPECIFIED
                )
                mView.measuredWidth
            } else {
                width
            }
        }
}