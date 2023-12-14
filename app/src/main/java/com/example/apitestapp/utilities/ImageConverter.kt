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
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Rect
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

@Composable
fun Bitmappable(
    modifier: Modifier = Modifier,
    content: @Composable BitmappableScope.() -> Unit
) {
    var contentBounds by remember {
        mutableStateOf<Rect?>(null)
    }

    Box(
        modifier = modifier
            .onGloballyPositioned {
                contentBounds = it.boundsInWindow()
            }
    ) {
        val view = LocalView.current
        BitmappableScopeImpl(view, contentBounds ?: Rect.Zero).content()
    }
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

@Preview
@Composable
fun BitmappablePreview() {
    var bitmap by remember {
        mutableStateOf<Bitmap?>(null)
    }

    DisposableEffect(Unit) {
        onDispose {
            bitmap?.recycle()
        }
    }

    var runConverter by remember {
        mutableStateOf(false)
    }
    Column(
        modifier = Modifier.padding(50.dp)
    ) {
        Bitmappable(
            modifier = Modifier
                .clickable(
                    indication = null,
                    interactionSource = remember { MutableInteractionSource() }
                ) {
                    runConverter = true
                }
        ) {
            LaunchedEffect(runConverter) {
                if (runConverter) {
                    bitmap = convertContentToBitmap()
                }
            }
            Image(painter = painterResource(id = R.drawable.coke), contentDescription = "")
        }

        Spacer(Modifier.height(16.dp))

        Box {
            if (bitmap != null) {
                Image(bitmap = bitmap!!.asImageBitmap(), contentDescription = null)
            } else {
                Box(Modifier.size(100.dp)) {
                    Text(text = "NO CONTENT", modifier = Modifier.size(100.dp))
                }
            }
        }
    }
}
