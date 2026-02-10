package com.dapaeng12.ruachmovie.utils

import android.text.Editable
import android.text.TextWatcher
import android.widget.EditText

fun EditText.onMyTextChanged(completion: (Editable?) -> Unit){
    this.addTextChangedListener(object : TextWatcher{

        // 글자가 변경 된 후
        override fun afterTextChanged(s: Editable?) {
            completion(s)
        }

        // 글자가 변경 되기 전
        override fun beforeTextChanged(
            s: CharSequence?,
            start: Int,
            count: Int,
            after: Int
        ) {
        }

        // 글자가 변경 중
        override fun onTextChanged(
            s: CharSequence?,
            start: Int,
            before: Int,
            count: Int
        ) {
        }
    })
}


// 문자열이 제이슨 형태인지, 제이슨 배열 형태인지
fun String?.isJsonObject() : Boolean {
    if (this?.startsWith("{") == true && this.endsWith("}")){
        return true
    } else {
        return false
    }
}
// 줄여서 이렇게도 표현 가능하다
//fun String?.isJsonObject() : Boolean = this?.startsWith("{") == true && this.endsWith("}")

// 문자열이 제이슨 배열인지
fun String?.isJsonArray() : Boolean{
    if (this?.startsWith("[") == true && this.endsWith("]")){
        return true
    } else {
        return false
    }
}
// 줄여서 이렇게도 표현 가능하다
//fun String?.isJsonObject() : Boolean = this?.startsWith("[") == true && this.endsWith("]")
