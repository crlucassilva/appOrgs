package br.com.alura.orgs.extensions

import android.os.Build.VERSION.SDK_INT
import android.widget.ImageView
import br.com.alura.orgs.R
import coil3.ImageLoader
import coil3.gif.AnimatedImageDecoder
import coil3.gif.GifDecoder
import coil3.load
import coil3.request.error
import coil3.request.fallback
import coil3.request.placeholder

fun ImageView.loadImage(url: String? = null) {

    val imageLoader = ImageLoader.Builder(this.context)
        .components {
            if (SDK_INT >= 28) {
                add(AnimatedImageDecoder.Factory())
            } else {
                add(GifDecoder.Factory())
            }
        }
        .build()

    load(url, imageLoader = imageLoader) {
        fallback(R.drawable.default_image)
        error(R.drawable.default_image)
        placeholder(R.drawable.placeholder)
    }
}
