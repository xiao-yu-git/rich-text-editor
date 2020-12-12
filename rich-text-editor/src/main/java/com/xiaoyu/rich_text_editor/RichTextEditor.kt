package com.xiaoyu.rich_text_editor

import android.content.Context
import android.util.AttributeSet
import com.xiaoyu.rich_text_editor.basic.editor.BasicRichTextEditor

/**
 * @author lmy
 * @Date 2020/12/12
 * 富文本编辑器
 */
class RichTextEditor : BasicRichTextEditor {
    constructor(context: Context) : super(context)

    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs)

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int)
            : super(context, attrs, defStyleAttr)
}