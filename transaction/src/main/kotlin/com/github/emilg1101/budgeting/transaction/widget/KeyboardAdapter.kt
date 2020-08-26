package com.github.emilg1101.budgeting.transaction.widget

import kotlin.text.StringBuilder

fun KeyboardView.setUpWithTextView(callback: AmountChangeCallback) {
    KeyboardAdapter(this, callback)
}

class KeyboardAdapter(keyboardView: KeyboardView, amountChangeCallback: AmountChangeCallback) {

    private var amountRaw = StringBuilder()

    init {
        keyboardView.setKeyListener(object : KeyboardView.KeyboardKeyListener {
            override fun onKeyPressed(key: Key) {
                val dotIndex = amountRaw.indexOf(".")
                if (key!= Key.KEY_DELETE && amountRaw.contains(".") && dotIndex + 2 < amountRaw.length) {
                   return
                }
                when (key) {
                    Key.KEY_ONE -> amountRaw.append(1)
                    Key.KEY_TWO -> amountRaw.append(2)
                    Key.KEY_THREE -> amountRaw.append(3)
                    Key.KEY_FOUR -> amountRaw.append(4)
                    Key.KEY_FIVE -> amountRaw.append(5)
                    Key.KEY_SIX -> amountRaw.append(6)
                    Key.KEY_SEVEN -> amountRaw.append(7)
                    Key.KEY_EIGHT -> amountRaw.append(8)
                    Key.KEY_NINE -> amountRaw.append(9)
                    Key.KEY_ZERO -> amountRaw.append(0)
                    Key.KEY_ACTION -> if (amountRaw.isNotEmpty() && !amountRaw.contains(".")) {
                        amountRaw.append(".")
                    }
                    Key.KEY_DELETE -> if (amountRaw.isNotEmpty()) {
                        if (amountRaw.contains(".") && amountRaw.lastOrNull() == '.') {
                            amountRaw.deleteCharAt(amountRaw.length - 1)
                            amountRaw.deleteCharAt(amountRaw.length - 1)
                        } else {
                            amountRaw.deleteCharAt(amountRaw.length - 1)
                            if (amountRaw.contains(".") && amountRaw.lastOrNull() == '.') {
                                amountRaw.deleteCharAt(amountRaw.length - 1)
                            }
                        }
                    }
                }
                amountChangeCallback.onAmountChanged(amountRaw.toString().toFloatOrNull() ?: let {
                    amountRaw.clear()
                    0f
                })
            }
        })
    }
}

interface AmountChangeCallback {
    fun onAmountChanged(amount: Float)
}
