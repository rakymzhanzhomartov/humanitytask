package kz.rakym.humanitytask.ui.progress


import android.animation.PropertyValuesHolder
import android.animation.ValueAnimator
import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.RectF
import android.util.AttributeSet
import android.view.View
import android.view.animation.AccelerateDecelerateInterpolator
import androidx.core.content.ContextCompat
import kz.rakym.humanitytask.R


class RotatedProgressView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : View(context, attrs, defStyleAttr) {

    private var radius: Float = 0f
    private val PROPERTY_RADIUS = "PROPERTY_RADIUS"
    private val PROPERTY_ROTATE = "PROPERTY_ROTATE"

    private val paint: Paint = Paint()

    init {
        paint.isAntiAlias = true
        paint.setColor(ContextCompat.getColor(context, R.color.light_blue))
        startAnimation()

    }

    private fun startAnimation() {
        val propertyRadius: PropertyValuesHolder =
            PropertyValuesHolder.ofFloat(PROPERTY_RADIUS, 0.0f, 137.5f)
        val propertyRotate: PropertyValuesHolder =
            PropertyValuesHolder.ofInt(PROPERTY_ROTATE, 0, 720)


        val animator = ValueAnimator()
        animator.setValues(propertyRadius, propertyRotate)
        animator.duration = 3000
        animator.interpolator = AccelerateDecelerateInterpolator()
        animator.addUpdateListener { animation ->
            radius = animation.getAnimatedValue(PROPERTY_RADIUS) as Float
            rotation = (animation.getAnimatedValue(PROPERTY_ROTATE) as Int).toFloat()
            invalidate()
        }
        animator.repeatCount = -1
        animator.repeatMode = ValueAnimator.REVERSE
        animator.start()
    }

    override fun onDraw(canvas: Canvas) {
        val rect = RectF(0f, 0f, width.toFloat(), height.toFloat())
        canvas.drawRoundRect(rect, radius, radius, paint);
    }

}