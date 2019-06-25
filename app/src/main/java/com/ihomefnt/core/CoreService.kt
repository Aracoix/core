package com.ihomefnt.core

import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface CoreService{
    @FormUrlEncoded
    @POST("/user/login")
    suspend fun login(@Field("username") userName: String, @Field("password") passWord: String): Call<BaseResponse<User>>

}
