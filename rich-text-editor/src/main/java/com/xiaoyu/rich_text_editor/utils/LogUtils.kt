package com.xiaoyu.rich_text_editor.utils

import android.util.Log
import com.xiaoyu.rich_text_editor.BuildConfig

/**
 * @author lmy
 * @Date 2020/12/11
 * Log 工具类
 */
object LogUtils {

    private val DEBUG = BuildConfig.DEBUG

    @JvmStatic
    fun i(tag: String = getFileLineMethod(), msg: String? = tag, tr: Throwable? = null) {
        if (DEBUG)
            Log.i(tag, msg, tr)
    }

    @JvmStatic
    fun d(tag: String = getFileLineMethod(), msg: String? = tag, tr: Throwable? = null) {
        if (DEBUG)
            Log.d(tag, msg, tr)
    }

    @JvmStatic
    fun e(tag: String = getFileLineMethod(), msg: String? = tag, tr: Throwable? = null) {
        if (DEBUG)
            Log.e(tag, msg, tr)
    }

    @JvmStatic
    fun v(tag: String = getFileLineMethod(), msg: String? = tag, tr: Throwable? = null) {
        if (DEBUG)
            Log.v(tag, msg, tr)
    }

    @JvmStatic
    fun w(tag: String = getFileLineMethod(), msg: String? = tag, tr: Throwable? = null) {
        if (DEBUG)
            Log.w(tag, msg, tr)
    }

    private fun getFileLineMethod() = run {
        val traceElement =
            Exception().stackTrace[2]
        StringBuffer("").append(traceElement.fileName)
            .append(" | ")
            .append(Thread.currentThread().id)
            .append(" | ")
            .append(traceElement.lineNumber)
            .append(" | ")
            .append(traceElement.methodName)
            .append("").toString()
    }
}