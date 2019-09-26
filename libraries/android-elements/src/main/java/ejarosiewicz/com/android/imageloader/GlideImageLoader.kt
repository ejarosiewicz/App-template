package ejarosiewicz.com.android.imageloader

import android.content.Context
import android.widget.ImageView
import com.bumptech.glide.Glide
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class GlideImageLoader @Inject constructor(private val context: Context) : ImageLoader {

    override fun load(site: String, imageView: ImageView) {
        Glide.with(context)
            .load(site)
            .into(imageView)
    }
}