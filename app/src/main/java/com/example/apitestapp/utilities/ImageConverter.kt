package com.example.apitestapp.utilities

import android.app.Activity
import android.content.Context
import android.content.ContextWrapper
import android.graphics.Bitmap
import android.os.Build
import android.os.Handler
import android.os.Looper
import android.view.PixelCopy
import android.view.View
import androidx.annotation.RequiresApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.layout.boundsInWindow
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalView
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.apitestapp.R
import kotlin.math.roundToInt

@Stable
interface BitmappableScope {
    @Stable
    suspend fun convertContentToBitmap(): Bitmap
}

@Stable
private class BitmappableScopeImpl(
    val view: View,
    val bounds: Rect
) : BitmappableScope {
    @RequiresApi(Build.VERSION_CODES.O)
    @Stable
    override suspend fun convertContentToBitmap() = view.clipContent(bounds)
}


@Stable
fun Context.getActivity(): Activity? =
    when (this) {
        is Activity -> this
        is ContextWrapper -> this.getActivity()

        else -> null
    }

@RequiresApi(Build.VERSION_CODES.O)
@Stable
fun View.clipContent(
    bounds: Rect
): Bitmap {
    val bitmap = Bitmap.createBitmap(
        bounds.width.roundToInt(),
        bounds.height.roundToInt(),
        Bitmap.Config.ARGB_8888
    )

    PixelCopy.request( // Android Oreo+
        checkNotNull(context.getActivity()).window,
        android.graphics.Rect(
            bounds.left.toInt(),
            bounds.top.toInt(),
            bounds.right.toInt(),
            bounds.bottom.toInt()
        ),
        bitmap, {}, Handler(Looper.getMainLooper())
    )

    return bitmap
}


@RequiresApi(Build.VERSION_CODES.O)
@Preview
@Composable
fun BitmappablePreview() {
    Column(
        modifier = Modifier.padding(50.dp)
    ) {
        Bitmappable()
    }
}


@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun Bitmappable(
    modifier: Modifier = Modifier,
) {
    var contentBounds by remember { mutableStateOf<Rect?>(null) }
    var bitmap by remember { mutableStateOf<Bitmap?>(null) }
    val view = LocalView.current
    Column(Modifier.background(color = Color.Gray)) {
        Box(
            modifier = modifier
                .onGloballyPositioned {
                    contentBounds = it.boundsInWindow()
                }
        ) {
            DisposableEffect(Unit) {
                onDispose {
                    bitmap?.recycle()
                }
            }
            LaunchedEffect(key1 = 1) {
                bitmap = contentBounds?.let { view.clipContent(it) }
                BitmappableScopeImpl(view, contentBounds ?: Rect.Zero)
            }
            if (bitmap == null)
                Image(
                    painter = painterResource(id = R.drawable.coke),
                    contentDescription = "",
                    Modifier
                        .width(300.dp)
                        .height(300.dp)
                )
        }
        Box(Modifier.background(color = Color.Gray)) {
            if (bitmap != null) {
                Image(
                    bitmap = bitmap!!.asImageBitmap(),
                    contentDescription = null,
                    Modifier.size(300.dp)
                )
            } else {
                Box(Modifier.size(10.dp)) {
                    Text(text = "NO CONTENT", modifier = Modifier.size(100.dp))
                }
            }
        }
    }
}