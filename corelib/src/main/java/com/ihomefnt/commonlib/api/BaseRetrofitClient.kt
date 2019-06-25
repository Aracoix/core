package com.ihomefnt.commonlib.api

import com.ihomefnt.commonlib.BuildConfig
import com.ihomefnt.commonlib.CommonLibInit
import okhttp3.Cache
import okhttp3.CacheControl
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.File
import java.util.concurrent.TimeUnit

/**
 * Created by Aracoix
 *  2018/6/118
 */
 object BaseRetrofitClient {

        private const val TIME_OUT = 5

    private val client: OkHttpClient
        get() {
            val builder = OkHttpClient.Builder()
            val logging = HttpLoggingInterceptor()
            if (BuildConfig.DEBUG) {
                logging.level = HttpLoggingInterceptor.Level.BODY
            } else {
                logging.level = HttpLoggingInterceptor.Level.BASIC
            }

            builder.addInterceptor(logging)
                    .connectTimeout(TIME_OUT.toLong(), TimeUnit.SECONDS)

            val httpCacheDirectory = File(CommonLibInit.application.cacheDir, "responses")
            val cacheSize = 10 * 1024 * 1024L // 10 MiB
            val cache = Cache(httpCacheDirectory, cacheSize)
            builder.cache(cache)
                .addInterceptor { chain ->
                    var request = chain.request()
                    request = request.newBuilder()
                        .cacheControl(CacheControl.FORCE_CACHE)
                        .build()
                    val response = chain.proceed(request)

                    response.newBuilder()
                        .build()
                    response
                }
            return builder.build()
        }


    fun <T> getService(serviceClass: Class<T>, baseUrl: String): T {
        return Retrofit.Builder()
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(baseUrl)
                .build().create(serviceClass)
    }
}
