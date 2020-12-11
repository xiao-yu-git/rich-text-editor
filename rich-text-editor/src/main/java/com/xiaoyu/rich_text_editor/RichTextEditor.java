package com.xiaoyu.rich_text_editor;

import android.content.Context;
import android.util.AttributeSet;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatEditText;

/**
 * @author lmy
 * @Date 2020/12/11
 * 富文本编辑器
 */
public class RichTextEditor extends AppCompatEditText {
    public RichTextEditor(@NonNull Context context) {
        super(context);
    }

    public RichTextEditor(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public RichTextEditor(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }
}
