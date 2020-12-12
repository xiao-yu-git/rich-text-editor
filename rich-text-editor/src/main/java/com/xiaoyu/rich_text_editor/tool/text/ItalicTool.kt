package com.xiaoyu.rich_text_editor.tool.text

import android.content.Context
import com.xiaoyu.rich_text_editor.R
import com.xiaoyu.rich_text_editor.span.text.ItalicSpan

/**
 * @author lmy
 * @Date 2020/12/12
 * 斜体
 */
class ItalicTool(context: Context) :
    BasicTextTool<ItalicSpan>(context, R.drawable.italic_nor) {

    override fun getSpan(): ItalicSpan {
        return ItalicSpan()
    }
}