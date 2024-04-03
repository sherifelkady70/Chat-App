package com.mis.route.chatapp

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.google.android.material.textfield.TextInputLayout

@BindingAdapter("app:error")
fun setErrorToTextInputLayout(textInputLayout: TextInputLayout, errorText: String?){
    textInputLayout.error = errorText
}

@BindingAdapter("app:imageConditions")
fun imageConditions(image:ImageView,category : String){
    if(category == Constants.SPORTS){
        image.setImageResource(R.drawable.image_sports_cat)
    }
    if(category == Constants.MOVIES){
        image.setImageResource(R.drawable.image_movies_cat)
    }
    if(category == Constants.MUSIC){
        image.setImageResource(R.drawable.image_music_cat)
    }
}
