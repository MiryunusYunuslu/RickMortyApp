package com.example.rickyandmortyapp.util

import android.widget.ImageView
import com.bumptech.glide.Glide

fun ImageView.setImageGlide(imageUrl: String) {
    Glide.with(context).load(imageUrl).into(this)
}