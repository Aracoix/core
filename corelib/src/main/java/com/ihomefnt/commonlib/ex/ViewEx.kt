package com.ihomefnt.commonlib.ex

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.annotation.Px
import androidx.core.content.ContextCompat
import org.greenrobot.eventbus.EventBus

/**
 * view exfun
 * Created by Aracoix on 2017/11/15.
 */
fun TextView.leftIcon(drawableId: Int) {
    setCompoundDrawablesWithIntrinsicBounds(ContextCompat.getDrawable(context, drawableId), null, null, null)
}
fun TextView.rightIcon(drawableId: Int) {
    setCompoundDrawablesWithIntrinsicBounds(null, null, ContextCompat.getDrawable(context, drawableId), null)
}



var View.scale: Float
    get() = Math.min(scaleX, scaleY)
    set(value) {
        scaleY = value
        scaleX = value
    }

fun View.addTopMargin(@Px marginInPx: Int) {
    val ayoutParams = this.layoutParams
    (layoutParams as ViewGroup.MarginLayoutParams).topMargin = marginInPx
    layoutParams = ayoutParams
}

fun View.addLeftMargin(@Px marginInPx: Int) {
    val ayoutParams = this.layoutParams
    (layoutParams as ViewGroup.MarginLayoutParams).leftMargin = marginInPx
    layoutParams = ayoutParams
}
fun View.addRightMargin(@Px marginInPx: Int) {
    val ayoutParams = this.layoutParams
    (layoutParams as ViewGroup.MarginLayoutParams).rightMargin = marginInPx
    layoutParams = ayoutParams
}

fun View.addBottomMargin(@Px marginInPx: Int) {
    val ayoutParams = this.layoutParams
    (layoutParams as ViewGroup.MarginLayoutParams).bottomMargin = marginInPx
    layoutParams = ayoutParams
}


fun View.show() {
    visibility = View.VISIBLE
}

fun View.hide() {
    visibility = View.GONE
}

fun View.invisible() {
    visibility = View.INVISIBLE
}



infix fun ViewGroup.inflate(layoutResId: Int): View =
        LayoutInflater.from(context).inflate(layoutResId, this, false)


operator fun ViewGroup.get(index: Int): View = getChildAt(index)