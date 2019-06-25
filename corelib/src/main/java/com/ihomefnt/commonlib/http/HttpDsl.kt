package com.ihomefnt.commonlib.http

import retrofit2.Call

/**
 *@Descripion httpUtils 的DSL
 *@Auther aracoix
 *@Date 2019-06-1816:55
 **/

class HttpDsl<T> {
    var api: (Call<T>)? = null

    internal var onSuccess: ((T?) -> Unit)? = null
        private set
    internal var onComplete: (() -> Unit)? = null
        private set
    internal  var onFailed: ((error: String?) -> Unit)? = null
        private set


    internal fun clean() {
        onSuccess = null
        onComplete = null
        onFailed = null
    }

    suspend fun onSuccess(block: (T?) -> Unit) {
        this.onSuccess = block
    }

   suspend fun onComplete(block: () -> Unit) {
        this.onComplete = block
    }

    suspend fun onFailed(block: (error: String?) -> Unit) {
        this.onFailed = block
    }

}
