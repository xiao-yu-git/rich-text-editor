package com.xiaoyu.rich_text_editor.tool.text

import android.content.Context
import android.graphics.drawable.Drawable
import android.text.Editable
import android.text.Spannable
import android.text.style.StyleSpan
import androidx.annotation.DrawableRes
import androidx.core.content.ContextCompat
import com.xiaoyu.rich_text_editor.R
import com.xiaoyu.rich_text_editor.basic.tool.BasicRichTextTool
import java.lang.reflect.ParameterizedType

/**
 * @author lmy
 * @Date 2020/12/12
 * 文本样式 工具的 basic 类
 * [STYLE_SPAN] 是[android.text.style.StyleSpan] 的子类型，一个工具只能有一个类型
 */
abstract class BasicTextTool<STYLE_SPAN : StyleSpan>(context: Context, @DrawableRes iconId: Int) : BasicRichTextTool(context) {

    private val mSpanClassType: Class<STYLE_SPAN> = getSpanClassForType()

    protected abstract fun getSpan(): STYLE_SPAN

    private var icon: Drawable? = null

    init {
        icon = ContextCompat.getDrawable(context, iconId)
        icon?.setBounds(0, 0, icon?.minimumWidth ?: 0, icon?.minimumHeight ?: 0)
    }

    override fun onToolClicked(text: Editable, selStart: Int, selEnd: Int) {
        setStatus(!getStatus())
        if (selStart != selEnd) {
            //取消样式
            val spans = text.getSpans(selStart, selEnd, mSpanClassType)
            spans.forEach { span ->
                text.removeSpan(span)
            }
            if (getStatus()) {
                //设置样式
                text.setSpan(getSpan(), selStart, selEnd, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
            }
        }
    }

    override fun applyStyle(text: Editable, start: Int, end: Int) {
        if (getStatus()) {
            text.setSpan(getSpan(), start, end, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
        }
    }

    override fun onSelectionChanged(text: Editable, selStart: Int, selEnd: Int) {
        if (selStart == selEnd) {
            if (selStart > 0) {
                //取出上一个字的样式
                val spans = text.getSpans(selStart - 1, selEnd, mSpanClassType)
                setStatus(spans.isNotEmpty())
            }
        } else {
            val spans = text.getSpans(selStart, selEnd, mSpanClassType)
            setStatus(spans.isNotEmpty())
        }
    }

    override fun getNormalIcon(): Drawable? {
        icon?.setTint(ContextCompat.getColor(mContext, R.color.nor_icon_color))
        return icon
    }

    override fun getSelectedIcon(): Drawable? {
        icon?.setTint(ContextCompat.getColor(mContext, R.color.sel_icon_color))
        return icon
    }

    //获取Span class 对象
    @Suppress("UNCHECKED_CAST")
    private fun getSpanClassForType(): Class<STYLE_SPAN> {
        return (javaClass.genericSuperclass as ParameterizedType).actualTypeArguments[0] as Class<STYLE_SPAN>
    }
}