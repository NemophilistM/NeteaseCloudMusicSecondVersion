package com.example.neteasecloudmusicsecondversionapplication.widget

import android.content.Context
import android.util.AttributeSet
import android.widget.RelativeLayout
import android.widget.EditText
import android.view.LayoutInflater
import android.widget.ImageView
import com.example.neteasecloudmusicsecondversionapplication.R

class EditTextSearchWidget(context: Context?, attrs: AttributeSet?) : RelativeLayout(context, attrs) {
    private val editText: EditText
    private val imageView: ImageView
    fun setOnclickListener(listener: OnClickListener) {
        imageView.setOnClickListener(listener)
    }

    fun getEditText(): String {
        return editText.text.toString().trim { it <= ' ' }
    }

    fun removeEditText() {
        editText.setText("")
    }

    fun setText(text: String) {
        editText.setText(text)
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
    }

    override fun onLayout(changed: Boolean, l: Int, t: Int, r: Int, b: Int) {
        super.onLayout(changed, l, t, r, b)
    }

    init {
        LayoutInflater.from(context).inflate(R.layout.widget_search, this)
        editText = findViewById(R.id.et_search)
        imageView = findViewById(R.id.iv_clear)
    }
}