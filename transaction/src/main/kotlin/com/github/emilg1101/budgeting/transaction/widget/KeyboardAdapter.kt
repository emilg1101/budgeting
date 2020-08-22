package com.github.emilg1101.budgeting.transaction.widget

import org.fabiomsr.moneytextview.MoneyTextView

fun KeyboardView.setUpWithTextView(textView: MoneyTextView) {
    KeyboardAdapter(this, textView)
}

class KeyboardAdapter(keyboardView: KeyboardView, textView: MoneyTextView) {

    private var amount = 0f

    init {
        keyboardView.setKeyListener(object : KeyboardView.KeyboardKeyListener {
            override fun onKeyPressed(key: Key) {
                when (key) {
                    Key.KEY_ONE -> amount = amount * 10 + 1
                    Key.KEY_TWO -> amount = amount * 10 + 2
                    Key.KEY_THREE -> amount = amount * 10 + 3
                    Key.KEY_FOUR -> amount = amount * 10 + 4
                    Key.KEY_FIVE -> amount = amount * 10 + 5
                    Key.KEY_SIX -> amount = amount * 10 + 6
                    Key.KEY_SEVEN -> amount = amount * 10 + 7
                    Key.KEY_EIGHT -> amount = amount * 10 + 8
                    Key.KEY_NINE -> amount = amount * 10 + 9
                    Key.KEY_ZERO -> amount *= 10
                    Key.KEY_ACTION -> amount = amount
                    Key.KEY_DELETE -> amount = amount / 10
                }
                textView.amount = amount
            }
        })
    }
}
