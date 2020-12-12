package com.xiaoyu.rich_text_editor.tool.text

import android.content.Context
import com.xiaoyu.rich_text_editor.R
import com.xiaoyu.rich_text_editor.span.text.BoldSpan

/**
 * @author lmy
 * @Date 2020/12/12
 * 粗体
 */
class BoldTool(context: Context) : BasicTextTool<BoldSpan>(context, R.drawable.bold_nor) {
    override fun getSpan(): BoldSpan {
        return BoldSpan()
    }
}