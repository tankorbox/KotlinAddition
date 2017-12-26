package com.example.honglinh.test.view.adapter

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.databinding.BindingAdapter
import android.view.View
import android.widget.ImageView
import com.squareup.picasso.Picasso

/**
 * Created by HoàngLinh on 12/6/2017.
 */
object ViewBindingAdapter {
    @BindingAdapter("isVisibleOrInvisible")
    @JvmStatic fun setViewVisibleOrNot(view: View, isLoading: Boolean) {
        if (isLoading) view.visibility = View.VISIBLE
        else view.visibility = View.GONE
    }

    @BindingAdapter("imageUrl")
    @JvmStatic fun loadImage(imageView: ImageView, url: String) {
        Picasso.with(imageView.context)
                .load(url)
                .into(imageView)
    }
}