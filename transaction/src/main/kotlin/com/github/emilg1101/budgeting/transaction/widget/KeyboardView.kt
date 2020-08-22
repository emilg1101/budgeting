package com.github.emilg1101.budgeting.transaction.widget

import android.content.Context
import android.util.AttributeSet
import android.view.View
import androidx.annotation.IdRes
import androidx.appcompat.widget.AppCompatTextView
import androidx.constraintlayout.widget.ConstraintLayout
import com.github.emilg1101.budgeting.transaction.R

class KeyboardView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : ConstraintLayout(context, attrs, defStyleAttr) {

    private var keyListener: KeyboardKeyListener? = null

    init {
        inflate(context, R.layout.layout_keyboard, this)
        setupView()
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        val resolvedHeight = View.resolveSize(
            resources.getDimensionPixelSize(R.dimen.ds_keyboard_default_height),
            heightMeasureSpec
        )
        super.onMeasure(
            widthMeasureSpec,
            MeasureSpec.makeMeasureSpec(resolvedHeight, MeasureSpec.EXACTLY)
        )
    }

    private fun setupView() {
        setButtonsListeners()
    }

    private fun setButtonsListeners() {
        Key.values().forEach { key ->
            findViewById<View>(key.id).setOnClickListener {
                keyListener?.onKeyPressed(key)
            }
        }
    }

    fun setKeyListener(keyListener: KeyboardKeyListener) {
        this.keyListener = keyListener
    }

    interface KeyboardKeyListener {
        fun onKeyPressed(key: Key)
    }
}

enum class Key(@IdRes val id: Int) {
    KEY_ONE(R.id.btn_1),
    KEY_TWO(R.id.btn_2),
    KEY_THREE(R.id.btn_3),
    KEY_FOUR(R.id.btn_4),
    KEY_FIVE(R.id.btn_5),
    KEY_SIX(R.id.btn_6),
    KEY_SEVEN(R.id.btn_7),
    KEY_EIGHT(R.id.btn_8),
    KEY_NINE(R.id.btn_9),
    KEY_ZERO(R.id.btn_0),
    KEY_ACTION(R.id.btn_action),
    KEY_DELETE(R.id.btn_delete);

    companion object {
        val numbers by lazy {
            arrayOf(
                KEY_ONE,
                KEY_TWO,
                KEY_THREE,
                KEY_FOUR,
                KEY_FIVE,
                KEY_SIX,
                KEY_SEVEN,
                KEY_EIGHT,
                KEY_NINE,
                KEY_ZERO
            )
        }
    }
}

class SquareTextView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : AppCompatTextView(context, attrs, defStyleAttr) {

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
        val width = measuredWidth
        val height = measuredHeight
        val size = width.coerceAtLeast(height)
        val widthSpec = MeasureSpec.makeMeasureSpec(size, MeasureSpec.EXACTLY)
        val heightSpec = MeasureSpec.makeMeasureSpec(size, MeasureSpec.EXACTLY)
        super.onMeasure(widthSpec, heightSpec)
    }
}
