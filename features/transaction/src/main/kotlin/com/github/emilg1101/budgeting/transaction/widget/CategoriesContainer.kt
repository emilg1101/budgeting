package com.github.emilg1101.budgeting.transaction.widget

import android.content.Context
import android.util.AttributeSet
import android.view.View
import androidx.constraintlayout.widget.ConstraintLayout

class CategoriesContainer @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : ConstraintLayout(context, attrs, defStyleAttr) {

    var mHeight = 0
        set(value) {
            field = value
            requestLayout()
        }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        val resolvedHeight = View.resolveSize(
            mHeight,
            heightMeasureSpec
        )
        super.onMeasure(
            widthMeasureSpec,
            MeasureSpec.makeMeasureSpec(resolvedHeight, MeasureSpec.EXACTLY)
        )
    }
}