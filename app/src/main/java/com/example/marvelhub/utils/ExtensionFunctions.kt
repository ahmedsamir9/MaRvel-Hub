package com.example.marvelhub.utils

import android.graphics.Bitmap
import android.graphics.ColorFilter
import android.media.Image
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.MultiTransformation
import com.bumptech.glide.request.RequestOptions
import com.bumptech.glide.request.RequestOptions.bitmapTransform
import jp.wasabeef.glide.transformations.BlurTransformation
import jp.wasabeef.glide.transformations.ColorFilterTransformation
import jp.wasabeef.glide.transformations.GrayscaleTransformation

fun ImageView.setBlurImageWithGlide(imageId :Int ){
    Glide.with(this)
        .load(imageId)
        .into(this)
}