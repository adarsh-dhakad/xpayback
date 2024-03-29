package adarsh.xpayback.utils

import android.content.Context
import android.util.TypedValue
import android.widget.ImageView
import com.bumptech.glide.Glide
import adarsh.xpayback.R

fun ImageView.loadUrl(url: String) {
    Glide.with(context)
        .load(url)
        .placeholder(R.drawable.light_background)
        .error(R.drawable.no_photo)
        .into(this)
}

fun Int.pxToDp(context: Context?):Int = TypedValue.applyDimension(
    TypedValue.COMPLEX_UNIT_DIP,
    this.toFloat(),
    context?.resources?.displayMetrics
).toInt()