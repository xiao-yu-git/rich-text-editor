package com.xiaoyu.rich_text_editor.drawable

import android.annotation.SuppressLint
import android.graphics.*
import android.graphics.drawable.Drawable
import android.view.View
import com.xiaoyu.rich_text_editor.R

/**
 * @author lmy
 * @Date 2020/11/30
 */
class PopupWindowBackground(private val mView: View) : Drawable() {
    private val mPaint = Paint()
    private val mPath = Path() //绘制路径
    private var radius: Float//圆角
    private var mPointerX = 0f //指针(小尖尖)中间 X 坐标

    private val pointerWidth: Float
    private val mPointerPadding: Float
    private val mRectF = RectF()
    private val radiusArray = FloatArray(8)

    init {
        mPaint.isAntiAlias = true
        mPaint.color = Color.WHITE
        mPaint.setShadowLayer(5f, 0f, 0f, Color.GRAY)
        radius = mView.resources.getDimension(R.dimen.window_radius)
        pointerWidth = mView.resources.getDimension(R.dimen.pointer_width) * 2
        mPointerPadding = mView.resources.getDimension(R.dimen.window_padding_size)
    }

    override fun draw(canvas: Canvas) {
        val width = mView.width
        val height = mView.height

        //小尖尖
        mPath.reset()
        val y = height - mPointerPadding * 2
        mPath.moveTo(pointerLeftX, y)
        mPath.lineTo(pointerCenterX, height.toFloat())
        mPath.lineTo(pointerRightX, y)
        mPath.setLastPoint(pointerRightX, y)
        //        canvas.drawPath(mPath, mPaint);


        //圆角 矩形
        mRectF[0f, 0f, width.toFloat()] = height - mPointerPadding
        mPath.addRoundRect(mRectF, getRadiusArray(), Path.Direction.CCW)
        canvas.drawPath(mPath, mPaint)
    }

    fun setPointerX(pointerX: Float) {
        mPointerX = pointerX
        invalidateSelf()
    }

    private val pointerLeftX: Float
        get() = pointerCenterX - pointerWidth / 2
    private val pointerRightX: Float
        get() = pointerCenterX + pointerWidth / 2

    fun setRadius(radius: Float) {
        this.radius = radius
        invalidateSelf()
    }

    private fun getRadiusArray(): FloatArray {
        radiusArray[0] = radius
        radiusArray[1] = radius
        radiusArray[2] = radius
        radiusArray[3] = radius
        radiusArray[4] = radius
        radiusArray[5] = radius
        radiusArray[6] = radius
        radiusArray[7] = radius
        return radiusArray
    }

    private val pointerCenterX: Float
        get() {
            if (mPointerX == 0f) {
                mPointerX = (mView.width shr 1).toFloat()
            }
            return mPointerX
        }

    override fun setAlpha(alpha: Int) {}
    override fun setColorFilter(colorFilter: ColorFilter?) {}

    @SuppressLint("WrongConstant")
    override fun getOpacity(): Int {
        return 0
    }
}