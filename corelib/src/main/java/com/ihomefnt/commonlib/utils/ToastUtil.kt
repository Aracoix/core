package com.ihomefnt.commonlib.utils

import android.widget.Toast

/**
 * toast工具类
 *
 * @author Aracoix
 */
object ToastUtil {

    var myToast: Toast? = null

    fun showShortToast(msg: String?) {
        if (myToast != null && msg != null) {
            myToast?.duration = Toast.LENGTH_SHORT
            myToast?.setText(msg)
            myToast?.show()
        }
    }

    fun showLongToast(msg: String?) {
        if (myToast != null && msg != null) {
            myToast?.duration = Toast.LENGTH_LONG
            myToast?.setText(msg)
            myToast?.show()
        }
    }

    fun showShortToast(msg_id: Int) {
        if (myToast != null) {
            myToast?.duration = Toast.LENGTH_LONG
            myToast?.setText(msg_id)
            myToast?.show()
        }
    }

    fun showLongToast(msg_id: Int) {
        if (myToast != null) {
            myToast?.duration = Toast.LENGTH_LONG
            myToast?.setText(msg_id)
            myToast?.show()
        }
    }
}