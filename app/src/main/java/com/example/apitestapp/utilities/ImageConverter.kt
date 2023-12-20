package com.example.apitestapp.utilities

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.*
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.apitestapp.R
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.io.IOException
import java.io.InputStream
import java.net.HttpURLConnection
import java.net.URL


@RequiresApi(Build.VERSION_CODES.Q)
@SuppressLint("CoroutineCreationDuringComposition")
@Composable
fun BitmapPreview(url: String? = null) {
    val scope = rememberCoroutineScope()
    var bitmap by remember { mutableStateOf<Bitmap?>(null) }
    val context = LocalContext.current
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        scope.launch(Dispatchers.Default) {
            getBitmapFromURL(url)?.let {
                removeBackGround(it, context).let {
                    bitmap = it
                }
            }
        }
    }
    if (bitmap == null) {
        Image(
            painter = painterResource(id = R.drawable.preload),
            contentDescription = "preload",
            modifier = Modifier.size(200.dp),
        )
    } else {
        bitmap?.asImageBitmap()?.let {
            Image(
                bitmap = it,
                contentDescription = null,
                Modifier
                    .size(200.dp)
            )
        }
    }
}

fun getBitmapFromURL(src: String?): Bitmap? {
    return try {
        val url = URL(src)
        val connection: HttpURLConnection = url.openConnection() as HttpURLConnection
        connection.doInput = true
        connection.connect()
        val input: InputStream = connection.inputStream
        BitmapFactory.decodeStream(input)
    } catch (e: IOException) {
        e.printStackTrace()
        null
    }
}

@RequiresApi(Build.VERSION_CODES.Q)
fun removeBackGround(image: Bitmap, context: Context): Bitmap? {
    val paintSrc = Paint()
    val paintBackground = Paint()
    val newBitmap = Bitmap.createBitmap(image.width, image.height, image.config)
    val background = BitmapFactory.decodeResource(context.resources, R.drawable.bgproducts)
    val canvas = Canvas(newBitmap)
    paintSrc.blendMode = BlendMode.MULTIPLY
    canvas.drawBitmap(background, 0f, 0f, paintBackground)
    canvas.drawBitmap(image, 0f, 0f, paintSrc)
    return newBitmap
}
