package com.example.tvshowtracker.utils

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso

class BindingAdapter {

    companion object {
        @JvmStatic
        @BindingAdapter("android:imageURL")
        fun setImageURL(imageView: ImageView, URL: String) {
            try {
                imageView.alpha = 0f
                Picasso.get().load(URL).noFade().into(imageView, object : Callback {
                    override fun onSuccess() {
                        imageView.animate().setDuration(300).alpha(1f).start()
                    }

                    override fun onError(e: java.lang.Exception?) {
                        TODO("Not yet implemented")
                    }
                })
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

}
