package com.xiaoyu.rich_text_editor.tool.text

import android.content.Context
import android.graphics.drawable.Drawable
import android.text.Editable
import android.text.Spannable
import android.view.LayoutInflater
import android.view.View
import androidx.annotation.IntDef
import androidx.core.content.ContextCompat
import com.xiaoyu.rich_text_editor.R
import com.xiaoyu.rich_text_editor.basic.RichPopupWindow
import com.xiaoyu.rich_text_editor.basic.tool.BasicRichTextTool
import com.xiaoyu.rich_text_editor.span.text.SizeSpan

/**
 * @author lmy
 * @Date 2020/12/12
 * 自体大小
 */
class SizeTool(context: Context) : BasicRichTextTool(context) {

    companion object {
        const val SP_14 = 14
        const val SP_16 = 16
        const val SP_18 = 18
        const val SP_20 = 20
        const val SP_22 = 22
        const val SP_24 = 24
        const val SP_26 = 26
        const val SP_28 = 28
        const val SP_30 = 30

        @IntDef(SP_14, SP_16, SP_18, SP_20, SP_22, SP_24, SP_26, SP_28, SP_30)
        @Retention(AnnotationRetention.SOURCE)
        annotation class Size
    }

    @Size
    private var mSize = SP_14
    private val mPopupView: View
    private var mPopupWindow: RichPopupWindow? = null

    init {
        setStatus(true)
        mPopupView = LayoutInflater.from(mContext).inflate(R.layout.text_size_popup_view, null)
    }

    override fun onToolClicked(text: Editable, selStart: Int, selEnd: Int) {
        if (mPopupWindow?.isShowing == true) {
            mPopupWindow?.dismiss()
        } else {
            if (mPopupWindow == null) {
                mPopupWindow = RichPopupWindow(mPopupView)
            }
            mPopupWindow?.show(getToolView())
        }
    }

    override fun applyStyle(text: Editable, start: Int, end: Int) {
        text.setSpan(SizeSpan(mSize), start, end, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
    }

    override fun onSelectionChanged(text: Editable, selStart: Int, selEnd: Int) {
    }

    override fun setStatus(status: Boolean) {
        super.setStatus(true)
    }

    override fun getNormalIcon(): Drawable? {
        return getSizeDrawable(SP_14)
    }

    override fun getSelectedIcon(): Drawable? {
        return getSizeDrawable(mSize)
    }

    private fun getSizeDrawable(@Size size: Int): Drawable? {
        val resId = when (size) {
            SP_14 -> R.drawable.font_size_14sp
            SP_16 -> R.drawable.font_size_16sp
            SP_18 -> R.drawable.font_size_18sp
            SP_20 -> R.drawable.font_size_20sp
            SP_22 -> R.drawable.font_size_22sp
            SP_24 -> R.drawable.font_size_24sp
            SP_26 -> R.drawable.font_size_26sp
            SP_28 -> R.drawable.font_size_28sp
            SP_30 -> R.drawable.font_size_30sp
            else -> R.drawable.font_size_14sp
        }
        val result = ContextCompat.getDrawable(mContext, resId)
        result?.setBounds(0, 0, result.minimumWidth, result.minimumHeight)
        return result
    }
}