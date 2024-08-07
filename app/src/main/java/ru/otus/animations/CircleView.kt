package ru.otus.animations

import android.animation.PropertyValuesHolder
import android.animation.ValueAnimator
import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.util.AttributeSet
import android.view.View
import android.view.animation.LinearInterpolator

class CircleView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : View(
    context,
    attrs,
    defStyleAttr
) {
    private var radius: Float = 0f
    private var alpha: Float = 0f

    private val paint = Paint().apply {
        color = resources.getColor(R.color.teal)
    }

    init {
        secondTask()
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        canvas.drawCircle(width / 2f, height / 2f, radius, paint)
    }

    fun secondTask() {
        val radiusHolder = PropertyValuesHolder.ofFloat("radius", 1f, 130f)
        val alphaHolder = PropertyValuesHolder.ofFloat("alpha", 0f, 1f)

        val animator = ValueAnimator.ofPropertyValuesHolder(radiusHolder, alphaHolder).apply {
            duration = 1000
            interpolator = LinearInterpolator()
            repeatCount = ValueAnimator.INFINITE
            repeatMode = ValueAnimator.RESTART
            addUpdateListener {
                radius = it.getAnimatedValue("radius") as Float
                alpha = it.getAnimatedValue("alpha") as Float
                invalidate()
            }
        }
        animator.start()
    }
}
