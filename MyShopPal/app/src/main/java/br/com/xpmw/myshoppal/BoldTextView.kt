package br.com.xpmw.myshoppal

import android.content.Context
import android.graphics.Typeface
import android.util.AttributeSet
import androidx.appcompat.widget.AppCompatTextView

class BoldTextView(context: Context, attributeSet: AttributeSet) :
    AppCompatTextView(context, attributeSet) {
    init {
        applayFont()
    }

    private fun applayFont() {
        val boldTypeFace: Typeface =
            Typeface.createFromAsset(context.assets, "Montserrat-Bold.ttf")
        typeface = boldTypeFace
    }
}