package com.example.apitestapp.utilities

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.View
import com.example.apitestapp.R

open class DrawPorterDuffView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0,
    defStyleRes: Int = 0
) : View(context, attrs, defStyleAttr, defStyleRes) {

    private val fullRect by lazy { Rect(0, 0, width, height) }
    private val paintDst = Paint()
    private val paintSrc = Paint()
    private var bitmapDestination: Bitmap? = null
    private var bitmapSource: Bitmap? = null

    fun loadBitmap(bitmap2: Bitmap) {

        val bitmap =
            BitmapFactory.decodeResource(context.resources, R.drawable.bgproducts)

        setLayerType(LAYER_TYPE_SOFTWARE, null)
        bitmapDestination = bitmap2
        bitmapSource = bitmap
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        if (width == 0 || height == 0) return

        try {
            paintSrc.xfermode = PorterDuffXfermode(PorterDuff.Mode.MULTIPLY)
            bitmapSource?.let { canvas.drawBitmap(it, null, fullRect, paintDst) }
            bitmapDestination?.let { canvas.drawBitmap(it, null, fullRect, paintSrc) }
        } catch (e: Exception) {
        }
    }

}