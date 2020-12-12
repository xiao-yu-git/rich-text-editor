package com.xiaoyu.rich_text_editor.span.text

import android.text.style.AbsoluteSizeSpan
import com.xiaoyu.rich_text_editor.tool.text.SizeTool

/**
 * @author lmy
 * @Date 2020/12/12
 */
class SizeSpan(@SizeTool.Companion.Size size: Int) : AbsoluteSizeSpan(size, true)