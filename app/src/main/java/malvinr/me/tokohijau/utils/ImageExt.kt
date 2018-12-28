package malvinr.me.tokohijau.utils

import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.MemoryCategory
import com.bumptech.glide.annotation.GlideModule
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.bumptech.glide.module.AppGlideModule
import com.bumptech.glide.request.RequestOptions

@GlideModule
class Images : AppGlideModule()

internal fun ImageView.loadImage(url: String?) {

    Glide.get(this.context).setMemoryCategory(MemoryCategory.NORMAL)

    GlideApp
        .with(this.context)
        .load(url)
        .centerCrop()
        .diskCacheStrategy(DiskCacheStrategy.RESOURCE)
        .transition(DrawableTransitionOptions.withCrossFade())
        .apply(RequestOptions.bitmapTransform(RoundedCorners(12)))
        .into(this)
}