package com.ddd.airplane.common.interfaces

interface EditText {

    fun setText(text: String?)
    fun getText(): String

    fun setOptionText(text: String?)

    fun clearFocus()
    fun setFocus(isFocus: Boolean)

}