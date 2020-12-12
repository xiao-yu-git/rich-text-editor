package com.xiaoyu.rich_text_editor_demo

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.LinearLayoutCompat
import com.xiaoyu.rich_text_editor.RichTextEditor
import com.xiaoyu.rich_text_editor.tool.text.BoldTool
import com.xiaoyu.rich_text_editor.tool.text.ItalicTool
import com.xiaoyu.rich_text_editor.tool.text.SizeTool

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val editor = findViewById<RichTextEditor>(R.id.editor)
        val toolLayout = findViewById<LinearLayoutCompat>(R.id.tool_layout)

        val boldTextTool = BoldTool(this)
        toolLayout.addView(boldTextTool.getToolView())
        val italicTextTool = ItalicTool(this)
        toolLayout.addView(italicTextTool.getToolView())
        val sizeTool = SizeTool(this)
        toolLayout.addView(sizeTool.getToolView())

        editor.addRichTextTool(boldTextTool)
        editor.addRichTextTool(italicTextTool)
        editor.addRichTextTool(sizeTool)
    }
}