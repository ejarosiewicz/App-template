package ejarosiewicz.com.android.imageloader

import android.widget.ImageView

interface ImageLoader {

    fun load(site: String, imageView: ImageView)
}