package com.xiaoyu.rich_text_editor.basic.editor

import android.content.Context
import android.graphics.Color
import android.text.Editable
import android.text.SpannableStringBuilder
import android.text.TextWatcher
import android.util.AttributeSet
import android.view.Gravity
import androidx.appcompat.widget.AppCompatEditText
import com.xiaoyu.rich_text_editor.basic.tool.IRichTextTool
import com.xiaoyu.rich_text_editor.utils.LogUtils

/**
 * @author lmy
 * @Date 2020/12/11
 * 基础的富文本编辑器
 */
open class BasicRichTextEditor : AppCompatEditText, IRichTextEditor {

    constructor(context: Context) : super(context) {
        initEditor()
    }

    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs) {
        initEditor()
    }

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int)
            : super(context, attrs, defStyleAttr) {
        initEditor()
    }

    private val empty = SpannableStringBuilder()

    /**
     * 编辑器工具集合
     */
    private val mToolList: MutableList<IRichTextTool>? = mutableListOf()

    /**
     * 文本输入观察者
     */
    private val mTextWatcher = object : TextWatcher {

        //开始位置
        private var start = 0

        //结束位置
        private var end = 0

        override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {

        }

        override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
            LogUtils.d(msg = "s = $s,start = $start,before=$before,count=$count")
            this.start = start
            this.end = start + count
        }

        override fun afterTextChanged(text: Editable) {
            //给所有的工具分发
            mToolList?.forEach { tool ->
                tool.applyStyle(text, start, end)
            }
        }
    }

    private fun initEditor() {
        //注册监听
        addTextChangedListener(mTextWatcher)
        //展示位置
        gravity = Gravity.TOP
        //设置背景颜色为透明（隐藏横线）
        setBackgroundColor(Color.TRANSPARENT)
        //设置文字大小 14sp
        textSize = 14f
    }

    override fun fromHtml(html: CharSequence) {

    }

    override fun getHtml(): SpannableStringBuilder {
        return SpannableStringBuilder()
    }

    override fun addRichTextTool(tool: IRichTextTool) {
        tool.attachEditor(this)
        mToolList?.add(tool)
    }

    override fun setRichTextTools(tools: List<IRichTextTool>) {
        //先将原有的Tool 卸载
        mToolList?.forEach { tool ->
            tool.detachEditor()
        }
        mToolList?.clear()
        //再挂载新的
        tools.forEach { tool ->
            tool.attachEditor(this)
        }
        mToolList?.addAll(tools)
    }

    override fun getEditable(): Editable {
        return text!!
    }

    override fun onDetachedFromWindow() {
        //移除监听
        removeTextChangedListener(mTextWatcher)
        //卸载所有工具
        mToolList?.forEach { tool ->
            tool.detachEditor()
        }
        super.onDetachedFromWindow()
    }

    /**
     * 当光标位置改变的时候回调
     * 这里需要的是选中情况下回调，即 [selStart]和[selEnd]不同的时候回调
     */
    override fun onSelectionChanged(selStart: Int, selEnd: Int) {
        super.onSelectionChanged(selStart, selEnd)
        LogUtils.d(msg = "selStart=$selStart,selEnd=$selEnd")
        mToolList?.forEach { tool ->
            tool.onSelectionChanged(text ?: empty, selStart, selEnd)
        }
    }
}