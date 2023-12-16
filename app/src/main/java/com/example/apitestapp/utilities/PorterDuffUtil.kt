package com.sei.android.sevennowdelivery.utility

import android.content.Context
import android.graphics.Bitmap
import android.graphics.drawable.Drawable
import com.bumptech.glide.Glide
import com.bumptech.glide.request.target.CustomTarget
import com.bumptech.glide.request.transition.Transition
import com.example.apitestapp.R
import com.example.apitestapp.utilities.DrawPorterDuffView
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class PorterDuffUtil {
    companion object {
        private fun loadBitmap(
            context: Context?,
            url: String?,
            view: DrawPorterDuffView,
        ) {
            context?.let {
                Glide.with(it)
                    .asBitmap()
                    .load(url)
                    .placeholder(R.drawable.senow_img_preload)
                    .into(object : CustomTarget<Bitmap?>() {
                        override fun onResourceReady(
                            resource: Bitmap,
                            ransition: Transition<in Bitmap?>?
                        ) {
                            view.loadBitmap(resource)
                        }

                        override fun onLoadCleared(placeholder: Drawable?) {}
                    })
            }
        }

        fun loadImage(
            context: Context?,
            url: String?,
            view: DrawPorterDuffView
        ) {
            CoroutineScope(Dispatchers.Main).launch {
                loadBitmap(context, url, view)
            }
        }

        fun loadImageWithScope(
            context: Context?,
            url: String?,
            view: DrawPorterDuffView,
            scope: CoroutineScope
        ) {
            scope.launch {
                loadBitmap(context, url, view)
            }
        }

    }
}