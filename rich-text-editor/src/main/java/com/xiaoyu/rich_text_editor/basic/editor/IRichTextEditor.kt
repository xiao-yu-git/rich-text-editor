package com.xiaoyu.rich_text_editor.basic.editor

import android.text.Editable
import android.text.SpannableStringBuilder
import com.xiaoyu.rich_text_editor.basic.tool.IRichTextTool

/**
 * @author lmy
 * @Date 2020/12/12
 * 富文本编辑器接口
 */
interface IRichTextEditor {
    /**
     * 解析富文本，并展示在编辑器上
     * [html] 一个富文本字符串
     */
    fun fromHtml(html: CharSequence)

    /**
     * 将编辑器里的文本转换成富文本
     * @return[SpannableStringBuilder] 一个富文本
     */
    fun getHtml(): SpannableStringBuilder

    /**
     * 添加一个编辑器工具
     */
    fun addRichTextTool(tool: IRichTextTool)

    /**
     * 设置一个编辑器工具集合,会覆盖掉之前的设置
     */
    fun setRichTextTools(tools: List<IRichTextTool>)

    /**
     * 获取编辑文本
     */
    fun getEditable(): Editable

    /**
     * 获取选中文本开始下标
     */
    fun getSelectionStart(): Int

    /**
     * 获取选中文本的结束下标
     */
    fun getSelectionEnd(): Int
}