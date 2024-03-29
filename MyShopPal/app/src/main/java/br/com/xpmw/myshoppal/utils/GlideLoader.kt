package br.com.xpmw.myshoppal.utils

import android.content.Context
import android.net.Uri
import android.widget.ImageView
import br.com.xpmw.myshoppal.R
import com.bumptech.glide.Glide
import java.io.IOException

class GlideLoader(val context: Context) {
    fun loadUserPicture(image: Any, imageView: ImageView) {
        try {
            //Load the user image in the imageView
            Glide.with(context)
                .load(image)
                .centerCrop() //Scale type of the image
                .placeholder(R.drawable.ic_user_placeholder) // A default place holder if image is failed to load.
                .into(imageView) // the view in which the image will be load
        }catch (e: IOException){
            e.printStackTrace()
        }
    }

    fun loadProductPicture(image: Any, imageView: ImageView) {
        try {
            //Load the user image in the imageView
            Glide
                .with(context)
                .load(image)
                .centerCrop() //Scale type of the image
                .into(imageView) // the view in which the image will be load
        }catch (e: IOException){
            e.printStackTrace()
        }
    }
}