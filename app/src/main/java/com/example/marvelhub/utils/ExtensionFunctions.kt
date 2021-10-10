package com.example.marvelhub.utils

import android.graphics.Bitmap
import android.graphics.ColorFilter
import android.media.Image
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.ProgressBar
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.FragmentActivity
import androidx.paging.LoadState
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.MultiTransformation
import com.bumptech.glide.request.RequestOptions
import com.bumptech.glide.request.RequestOptions.bitmapTransform
import com.example.marvelhub.R
import com.example.marvelhub.data.remote.model.characterresponse.Thumbnail
import com.example.marvelhub.domain.model.EventData
import com.example.marvelhub.presentation.DeatilsScreen.adapter.EventPagingAdapter
import eightbitlab.com.blurview.BlurView
import eightbitlab.com.blurview.RenderScriptBlur
import jp.wasabeef.glide.transformations.BlurTransformation
import jp.wasabeef.glide.transformations.ColorFilterTransformation
import jp.wasabeef.glide.transformations.GrayscaleTransformation
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

fun ImageView.setImage(imagePath :String ){
    Glide.with(this)
        .load(imagePath)
        .placeholder(R.drawable.loading_animation)
        .error(R.drawable.image_placeholder)
        .into(this)
}
fun BlurView.startBlur(currentActivity: FragmentActivity){
    val radius = 20f
    val decorView: View = currentActivity.window.decorView ;
    val rootView = decorView.findViewById<View>(android.R.id.content) as ViewGroup
    val windowBackground = decorView.background
    this.setupWith(rootView)
        .setFrameClearDrawable(windowBackground)
        .setBlurAlgorithm(RenderScriptBlur(context))
        .setBlurRadius(radius)
        .setBlurAutoUpdate(true)
        .setHasFixedTransformationMatrix(true) // Or false if it's in a scrolling container or might be animated

}
fun String.imagePathConcatenation(thumbnail: Thumbnail?): String {
    if (thumbnail == null)return ""
    return "${thumbnail.path}.${thumbnail.extension}";
}
fun String.fillCharacterEmptyDescription(): String {
    if (this.isNullOrBlank())
    return "Sorry We Have No Data For This Character"
    return this
}
fun SearchView.getQueryTextChangeStateFlow(): StateFlow<String> {

    val query = MutableStateFlow("")

    setOnQueryTextListener(object : SearchView.OnQueryTextListener {
        override fun onQueryTextSubmit(query: String?): Boolean {
            return true
        }

        override fun onQueryTextChange(newText: String): Boolean {
            query.value = newText
            return true
        }
    })

    return query

}
