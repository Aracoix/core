package com.ihomefnt.commonlib.http

import kotlinx.coroutines.*
import retrofit2.Call
import java.net.ConnectException


fun <T> CoroutineScope.http(dsl:suspend HttpDsl<T>.() -> Unit
) {
    this.launch(Dispatchers.Main) {
        val retrofitCoroutine = HttpDsl<T>()
        retrofitCoroutine.dsl()
        retrofitCoroutine.api?.let { call ->
            val work = async(Dispatchers.IO) { // io线程执行
                try {
                    call.execute()
                } catch (e: ConnectException) {
                    e.printStackTrace()
                    retrofitCoroutine.onFailed?.invoke("网络连接出错")
                    null
                } catch (e: Exception) {
                    retrofitCoroutine.onFailed?.invoke("未知网络错误")
                    null
                }
            }
            work.join()
            work.invokeOnCompletion {
                // 协程关闭时，取消任务
                if (work.isCancelled) {
                    call.cancel()
                    retrofitCoroutine.clean()
                }
            }
            val response = work.await()
            retrofitCoroutine.onComplete?.invoke()
            response?.let {
                if (response.isSuccessful) {
                    retrofitCoroutine.onSuccess?.invoke(response.body())
                } else {
                    retrofitCoroutine.onFailed?.invoke(response.errorBody().toString())
                }
            }
        }
    }
}


