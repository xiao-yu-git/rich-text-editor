package com.xiaoyu.rich_text_editor.basic.tool

import android.content.Context
import android.text.Editable
import android.view.View
import com.xiaoyu.rich_text_editor.basic.editor.IRichTextEditor

/**
 * @author lmy
 * @Date 2020/12/12
 * 富文本工具接口
 */
interface IRichTextTool {

    /**
     * 挂载到上编辑器
     */
    fun attachEditor(editor: IRichTextEditor)

    /**
     * 获取挂载的编辑器
     */
    fun getEditor(): IRichTextEditor

    /**
     * 获取工具展示的View
     */
    fun getToolView(): View

    /**
     * 创建ToolView
     */
    fun createToolView(context: Context): View

    /**
     * 当ToolView 创建时回调
     */
    fun onToolViewCreated(toolView: View)

    /**
     * 当Tool 被点击时回调
     */
    fun onToolClicked(text: Editable, selStart: Int, selEnd: Int)

    /**
     * 设置状态
     */
    fun setStatus(status: Boolean)

    /**
     * 获取状态
     */
    fun getStatus(): Boolean

    /**
     *当状态改变的时候
     */
    fun onStatusChanged()

    /**
     *输入的时候设置样式
     * [text] 编辑的文本
     * [start]本次修改的开始位置
     * [end] 本次修改的结束位置
     * 区间关系[[start],[end])
     */
    fun applyStyle(text: Editable, start: Int, end: Int)

    /**
     * 光标选中的的情况下回调
     * [text] 编辑的文本
     * [selStart]本次修改的开始位置
     * [selEnd] 本次修改的结束位置
     * 区间关系[[selStart],[selEnd])
     */
    fun onSelectionChanged(text: Editable, selStart: Int, selEnd: Int)

    /**
     *从编辑器上卸载
     */
    fun detachEditor()
}