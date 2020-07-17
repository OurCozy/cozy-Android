package com.example.cozy

import android.text.Editable
import android.text.TextWatcher
import android.widget.EditText

fun EditText.textChangedListener(onChanged: (CharSequence?) -> Unit) {
    this.addTextChangedListener(object :TextWatcher{
        override fun afterTextChanged(s: Editable?) {
        }

        override fun beforeTextChanged(s: CharSequence?, p1: Int, p2: Int, p3: Int) {
        }

        override fun onTextChanged(s: CharSequence?, p1: Int, p2: Int, p3: Int) {
            onChanged(s)
        }
    })
}