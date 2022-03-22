package kz.rakym.humanitytask.ui.utils

import android.content.res.Resources
import android.graphics.*
import android.graphics.Matrix.ScaleToFit
import android.graphics.drawable.BitmapDrawable


class BackgroundBitmapDrawable(res: Resources, bitmap: Bitmap) :
    BitmapDrawable(res, bitmap) {
    private val mMatrix: Matrix = Matrix()
    private var moldHeight = 0
    var simpleMapping = false
    override fun onBoundsChange(bounds: Rect) {
        if (bounds.height() > moldHeight) {
            moldHeight = bounds.height()
            val b = bitmap
            val src = RectF(0f, 0f, b.width.toFloat(), b.height.toFloat())
            val dst: RectF
            if (simpleMapping) {
                dst = RectF(bounds)
                mMatrix.setRectToRect(src, dst, ScaleToFit.CENTER)
            } else {
                // Full Screen Image -> Always scale and center-crop in order to fill the screen
                val dwidth = src.width()
                val dheight = src.height()
                val vwidth: Float = bounds.width().toFloat()
                val vheight: Float = bounds.height().toFloat()
                val scale: Float
                var dx = 0f
                var dy = 0f
                if (dwidth * vheight > vwidth * dheight) {
                    scale = vheight / dheight
                    dx = (vwidth - dwidth * scale) * 0.5f
                } else {
                    scale = vwidth / dwidth
                    dy = (vheight - dheight * scale) * 0.5f
                }
                mMatrix.setScale(scale, scale)
                mMatrix.postTranslate(dx, dy)
            }
        }
    }

    override fun draw(canvas: Canvas) {
        canvas.drawColor(-0x55ff0100)
        canvas.drawBitmap(bitmap, mMatrix, null)
    }
}