package com.xiaoyu.rich_text_editor.basic.tool

import android.content.Context
import android.graphics.Color
import android.graphics.drawable.Drawable
import android.view.View
import android.view.ViewGroup.LayoutParams
import android.widget.ImageButton
import android.widget.ImageView
import com.xiaoyu.rich_text_editor.R
import com.xiaoyu.rich_text_editor.basic.editor.IRichTextEditor
import com.xiaoyu.rich_text_editor.utils.LogUtils

/**
 * @author lmy
 * @Date 2020/12/12
 */
abstract class BasicRichTextTool(protected val mContext: Context) : IRichTextTool {

    private var mEditor: IRichTextEditor? = null

    private var mToolView: View? = null

    //是否应用样式
    private var mStatus: Boolean = false

    override fun getToolView(): View {
        if (mToolView == null) {
            mToolView = createToolView(mContext)
            onToolViewCreated(mToolView!!)
            mToolView!!.setOnClickListener {
                if (mEditor == null) {
                    LogUtils.e(msg = "mEditor = null")
                } else {
                    onToolClicked(mEditor!!.getEditable(), mEditor!!.getSelectionStart(), mEditor!!.getSelectionEnd())
                }
            }
        }
        return mToolView!!
    }

    protected open fun getNormalIcon(): Drawable? {
        return null
    }

    protected open fun getSelectedIcon(): Drawable? {
        return null
    }

    override fun createToolView(context: Context): View {
        val toolView = ImageButton(context)
        val toolViewSize = context.resources.getDimensionPixelOffset(R.dimen.tool_view_size)
        toolView.layoutParams = LayoutParams(toolViewSize, toolViewSize)
        toolView.setImageDrawable(getNormalIcon())
        toolView.setBackgroundColor(Color.TRANSPARENT)
        return toolView
    }

    override fun onToolViewCreated(toolView: View) {
    }

    private fun updateToolViewIcon() {
        if (mToolView is ImageView) {
            if (getStatus()) {
                (mToolView as ImageView).setImageDrawable(getSelectedIcon())
            } else {
                (mToolView as ImageView).setImageDrawable(getNormalIcon())
            }
        }
    }

    override fun onStatusChanged() {
    }

    override fun setStatus(status: Boolean) {
        this.mStatus = status
        onStatusChanged()
        updateToolViewIcon()
    }

    override fun getStatus(): Boolean {
        return mStatus
    }

    override fun attachEditor(editor: IRichTextEditor) {
        mEditor = editor
    }

    override fun getEditor(): IRichTextEditor {
        return mEditor!!
    }

    override fun detachEditor() {
        mEditor = null
    }
}