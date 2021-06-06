package br.com.xpmw.myshoppal.utils

import android.content.Context
import android.net.Uri
import android.widget.ImageView
import br.com.xpmw.myshoppal.R
import com.bumptech.glide.Glide
import java.io.IOException

class GliderLoader(val context: Context) {
    fun loadUserPicture(imageUri: Uri, imageView: ImageView) {
        try {
            //Load the user image in the imageView
            Glide.with(context)
                .load(imageUri)
                .centerCrop() //Scale type of the image
                .placeholder(R.drawable.ic_user_placeholder) // A default place holder if image is failed to load.
                .into(imageView) // the view in which the image will be load
        }catch (e: IOException){
            e.printStackTrace()
        }
    }
}